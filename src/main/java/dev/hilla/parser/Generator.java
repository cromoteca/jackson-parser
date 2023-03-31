package dev.hilla.parser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Generator {

  public String generateEndpoint(ScanResult.EndpointClass result) {
    return """
    declare type EndpointRequestInit = {};
    declare function _call(endpoint: string, method: string, params?: any, __init?: EndpointRequestInit): Promise<any>;
    declare const _client: { call: typeof _call };

    %s

    const %s = {
    %s
    };

    export default %s;
    """
        .formatted(
            generateMethodImplementations(result),
            result.getName(),
            generateMethodList(result),
            result.getName());
  }

  private String generateMethodImplementations(ScanResult.EndpointClass result) {
    return result.methods().stream()
        .sorted(Comparator.comparing(AnnotatedMethod::getName))
        .map(method -> generateMethod(method, result.type().getBeanClass().getName()))
        .collect(Collectors.joining("\n\n"));
  }

  private String generateMethodList(ScanResult.EndpointClass result) {
    return result.methods().stream()
        .sorted(Comparator.comparing(AnnotatedMethod::getName))
        .map(method -> """
        \s   %s,""".formatted(method.getName()))
        .collect(Collectors.joining("\n"));
  }

  private String generateMethod(AnnotatedMethod method, String className) {
    return """
        const %s = async (%s): Promise<%s> => {
            return _client.call('%s', '%s', {%s}, %s);
        };"""
        .formatted(
            method.getName(),
            generateParamList(method),
            generateType(method.getType()),
            className,
            method.getName(),
            generateParamNameList(method),
            chooseInitParamName(method));
  }

  private String generateParamList(AnnotatedMethod method) {
    var params = new ArrayList<String>();

    for (var i = 0; i < method.getParameterCount(); i++) {
      var param = method.getParameter(i);
      var javaParam = method.getAnnotated().getParameters()[i];
      params.add(javaParam.getName() + ": " + generateType(param.getType()));
    }

    params.add(chooseInitParamName(method) + "?: EndpointRequestInit");

    return String.join(", ", params);
  }

  private String generateParamNameList(AnnotatedMethod method) {
    var params =
        Arrays.stream(method.getAnnotated().getParameters())
            .map(param -> param.getName())
            .collect(Collectors.joining(", "));
    return params.isEmpty() ? "" : ' ' + params + ' ';
  }

  private String chooseInitParamName(AnnotatedMethod method) {
    var initParam = "init";
    var names =
        Arrays.stream(method.getAnnotated().getParameters())
            .map(param -> param.getName())
            .collect(Collectors.toList());

    while (names.contains(initParam)) {
      initParam = '_' + initParam;
    }

    return initParam;
  }

  private String generateType(JavaType type) {
    if (type.isCollectionLikeType()) {
      return generateType(type.getContentType()) + "[]";
    }

    if (type.isArrayType()) {
      return generateType(type.getContentType()) + "[]";
    }

    if (type.isMapLikeType()) {
      return "Map<"
          + generateType(type.getKeyType())
          + ", "
          + generateType(type.getContentType())
          + '>';
    }

    return mapType(type.getRawClass().getName());
  }

  private String mapType(String typeName) {
    return switch (typeName) {
      case "java.lang.String" -> "string";
      case "int", "long", "float", "double" -> "number";
      default -> typeName;
    };
  }
}

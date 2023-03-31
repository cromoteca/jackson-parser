package dev.hilla.parser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.util.ArrayList;
import java.util.Arrays;
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
        .map(method -> generateMethod(method, result.type().getBeanClass().getName()))
        .collect(Collectors.joining(""));
  }

  private String generateMethodList(ScanResult.EndpointClass result) {
    return result.methods().stream()
        .map(method -> """
        %s,""".formatted(method.getName()))
        .collect(Collectors.joining(""));
  }

  private String generateMethod(AnnotatedMethod method, String className) {
    return """
        const %s = async (%s): Promise<%s> => {
            return _client.call('%s', '%s', { %s }, init);
        };"""
        .formatted(
            method.getName(),
            generateParamList(method),
            generateType(method.getType()),
            className,
            method.getName(),
            generateParamNameList(method));
  }

  private String generateParamList(AnnotatedMethod method) {
    var params = new ArrayList<String>();
    var paramNames = new ArrayList<String>();

    for (var i = 0; i < method.getParameterCount(); i++) {
      var param = method.getParameter(i);
      var javaParam = method.getAnnotated().getParameters()[i];
      paramNames.add(javaParam.getName());
      params.add(javaParam.getName() + ": " + generateType(param.getType()));
    }

    var initParam = "init";

    while (paramNames.contains(initParam)) {
      initParam = '_' + initParam;
    }

    params.add(initParam + "?: EndpointRequestInit");

    return String.join(", ", params);
  }

  private String generateParamNameList(AnnotatedMethod method) {
    return Arrays.stream(method.getAnnotated().getParameters())
        .map(param -> param.getName())
        .collect(Collectors.joining(", "));
  }

  private String generateType(JavaType type) {
    return mapType(type.getRawClass().getName());
  }

  private String mapType(String typeName) {
    return switch (typeName) {
      case "java.lang.String" -> "string";
      default -> typeName;
    };
  }
}

package com.cromoteca.generator;

import static com.cromoteca.generator.types.TypeHandler.EndpointMethodType.*;

import com.cromoteca.generator.types.TypeHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodMaker {
  private final MakerTools tools;
  private final AnnotatedMethod method;
  private final String className;
  private final TypeHandler.EndpointMethodType methodType;
  private final String clientVariableName;

  public MethodMaker(MakerTools tools, AnnotatedMethod method, String className) {
    this.tools = tools;
    this.method = method;
    this.className = className;
    methodType = tools.handlerFor(method.getRawType()).endpointMethodType();
    clientVariableName = tools.fromImport("client", "/connect-client.default", true, false);
  }

  public String generate() {
    return switch (methodType) {
      case CALL -> StringTemplate.from(
          """
                    async function ${methodName}${typeParams}(${paramList}): Promise<${returnType}> {
                        return ${client}.call('${className}', '${methodName}', {${paramNames}}, ${initParam});
                    }""",
          this);
      case SUBSCRIBE -> StringTemplate.from(
          """
                    function ${methodName}${typeParams}(${paramList}): ${subscription}<${returnType}> {
                        return ${client}.subscribe('${className}', '${methodName}', {${paramNames}});
                    }""",
          this);
    };
  }

  public String subscription() {
    return tools.fromImport("Subscription", "@hilla/frontend", false, true);
  }

  public String className() {
    return className;
  }

  public String client() {
    return clientVariableName;
  }

  public String methodName() {
    return method.getName();
  }

  public String returnType() {
    return switch (methodType) {
      case CALL -> tools.generateType(
          new FullType(
              method.getType(),
              method.getAnnotated().getAnnotatedReturnType(),
              method.getAnnotated().getReturnType()));
      case SUBSCRIBE -> tools.generateType(
          new FullType(
                  method.getType(),
                  method.getAnnotated().getAnnotatedReturnType(),
                  method.getAnnotated().getReturnType())
              .parameterizedTypes()
              .flatMap(Stream::findFirst)
              .orElseThrow());
    };
  }

  public String typeParams() {
    return tools.generateTypeParams(method.getAnnotated().getTypeParameters());
  }

  public String paramList() {
    var params = new ArrayList<String>();

    for (var i = 0; i < method.getParameterCount(); i++) {
      var param = method.getParameter(i);
      var generic = method.getAnnotated().getAnnotatedParameterTypes()[i];
      var javaParam = method.getAnnotated().getParameters()[i];
      params.add(
          javaParam.getName()
              + ": "
              + tools.generateType(new FullType(param.getType(), generic, javaParam)));
    }

    if (methodType == CALL) {
      params.add(
          initParam()
              + "?: "
              + tools.fromImport("EndpointRequestInit", "@hilla/frontend", false, true));
    }

    return String.join(", ", params);
  }

  public String initParam() {
    var initParam = "init";
    var names =
        Arrays.stream(method.getAnnotated().getParameters()).map(Parameter::getName).toList();

    while (names.contains(initParam)) {
      initParam = '_' + initParam;
    }

    return initParam;
  }

  public String paramNames() {
    var params =
        Arrays.stream(method.getAnnotated().getParameters())
            .map(Parameter::getName)
            .collect(Collectors.joining(", "));
    return params.isEmpty() ? "" : ' ' + params + ' ';
  }
}

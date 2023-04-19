package com.cromoteca.generator;

import static com.cromoteca.generator.types.DefaultTypeHandler.EndpointMethodType.*;

import com.cromoteca.generator.types.DefaultTypeHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MethodMaker {
  private final Generator.MakerTools tools;
  private final AnnotatedMethod method;
  private final String className;
  private final DefaultTypeHandler.EndpointMethodType methodType;
  private final String clientVariableName;
  private final SortedSet<String> typeVariables;
  private final List<String> params;
  private final String returnType;

  MethodMaker(Generator.MakerTools tools, AnnotatedMethod method, String className) {
    this.tools = tools;
    this.method = method;
    this.className = className;
    typeVariables = new TreeSet<>();
    methodType = tools.handlerFor(method.getRawType()).endpointMethodType();
    clientVariableName = tools.fromImport("client", "/connect-client.default", true, false);
    params = generateParamList();
    returnType = generateReturnType();
  }

  String generate() {
    return switch (methodType) {
      case CALL -> StringTemplate.from(
          """
          async function ${methodName}${typeVars}(${paramList}): Promise<${returnType}> {
              return ${client}.call('${className}', '${methodName}', {${paramNames}}, ${initParam});
          }""",
          this);
      case SUBSCRIBE -> StringTemplate.from(
          """
          function ${methodName}${typeVars}(${paramList}): ${subscription}<${returnType}> {
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
    return returnType;
  }

  private String generateReturnType() {
    FullType returnType =
        new FullType(
            method.getType(),
            method.getAnnotated().getAnnotatedReturnType(),
            method.getAnnotated().getReturnType());

    if (methodType == SUBSCRIBE) {
      returnType = returnType.parameterizedTypes().flatMap(Stream::findFirst).orElseThrow();
    }

    return tools.generateType(returnType, typeVariables);
  }

  public String typeVars() {
    return tools.generateTypeVariables(typeVariables);
  }

  public String paramList() {
    return String.join(", ", params);
  }

  private ArrayList<String> generateParamList() {
    var params = new ArrayList<String>();

    for (var i = 0; i < method.getParameterCount(); i++) {
      var param = method.getParameter(i);
      var generic = method.getAnnotated().getAnnotatedParameterTypes()[i];
      var javaParam = method.getAnnotated().getParameters()[i];
      params.add(
          javaParam.getName()
              + ": "
              + tools.generateType(
                  new FullType(param.getType(), generic, javaParam), typeVariables));
    }

    if (methodType == CALL) {
      params.add(
          initParam()
              + "?: "
              + tools.fromImport("EndpointRequestInit", "@hilla/frontend", false, true));
    }
    return params;
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

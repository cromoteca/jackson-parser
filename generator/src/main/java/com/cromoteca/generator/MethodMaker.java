/*
 * Copyright (C) 2023 Luciano Vernaschi (luciano at cromoteca.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cromoteca.generator;

import static com.cromoteca.generator.types.DefaultTypeHandler.EndpointMethodType.*;

import com.cromoteca.generator.types.DefaultTypeHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.vaadin.hilla.Loader;
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
    if (method.hasAnnotation(Loader.class)) {
      return StringTemplate.from(
          """
          async function ${methodName}${typeVars}({ params }: ${loaderParamType}): Promise<${returnType}> {
              return ${client}.call('${className}', '${methodName}', {${loaderParams}});
          }""",
          this);
    }

    return switch (methodType) {
      case CALL ->
          StringTemplate.from(
              """
          async function ${methodName}${typeVars}(${paramList}): Promise<${returnType}> {
              return ${client}.call('${className}', '${methodName}', {${paramNames}}, ${initParam});
          }""",
              this);
      case SUBSCRIBE ->
          StringTemplate.from(
              """
          function ${methodName}${typeVars}(${paramList}): ${subscription}<${returnType}> {
              return ${client}.subscribe('${className}', '${methodName}', {${paramNames}});
          }""",
              this);
    };
  }

  public String loaderParamType() {
    return tools.fromImport("LoaderFunctionArgs", "react-router-dom", false, true);
  }

  public String loaderParams() {
    var params =
        Arrays.stream(method.getAnnotated().getParameters())
            .map(
                p -> {
                  var name = "params." + p.getName();
                  var caster = tools.handlerFor(p.getType()).caster();

                  if (caster != null) {
                    name = String.format(caster, name);
                  }

                  return p.getName() + ": " + name;
                })
            .collect(Collectors.joining(", "));
    return params.isEmpty() ? "" : ' ' + params + ' ';
  }

  public String subscription() {
    return tools.fromImport("Subscription", "@vaadin/hilla-frontend", false, true);
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
              + tools.fromImport("EndpointRequestInit", "@vaadin/hilla-frontend", false, true));
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

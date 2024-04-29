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

import com.cromoteca.ScanResult;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class EndpointMaker {
  private final Generator.MakerTools tools;
  private final ScanResult.EndpointClass endpoint;
  private final String methodImplementations;

  EndpointMaker(Generator.MakerTools tools, ScanResult.EndpointClass endpoint) {
    this.tools = tools;
    this.endpoint = endpoint;
    methodImplementations = String.join("\n\n", generateMethodImplementations());
  }

  String generate() {
    return StringTemplate.from(
        """
        ${imports}${methodImplementations}

        const ${name} = {
        ${methodList}
        };

        export default ${name};
        """,
        this);
  }

  public String imports() {
    return tools.getImports();
  }

  public String name() {
    return endpoint.getName();
  }

  public String methodImplementations() {
    return methodImplementations;
  }

  private List<String> generateMethodImplementations() {
    return endpoint.methods().stream()
        .sorted(Comparator.comparing(AnnotatedMethod::getName))
        .map(
            method ->
                new MethodMaker(tools, method, endpoint.type().getBeanClass().getName()).generate())
        .toList();
  }

  public String methodList() {
    return endpoint.methods().stream()
        .sorted(Comparator.comparing(AnnotatedMethod::getName))
        .map(
            method ->
                    """
              \s   %s,"""
                    .formatted(method.getName()))
        .collect(Collectors.joining("\n"));
  }
}

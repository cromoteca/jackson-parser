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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

class EntityMaker {
  private final Generator.MakerTools tools;
  private final ScanResult.EntityClass entity;
  private final List<String> properties;
  private final SortedSet<String> typeVariables;

  EntityMaker(Generator.MakerTools tools, ScanResult.EntityClass entity) {
    this.tools = tools;
    this.entity = entity;
    typeVariables = new TreeSet<>();
    tools.addKeyword(entity.getName());
    properties =
        tools.handlerFor(entity.type()).generateEntityProperties()
            ? generateProperties()
            : List.of();
  }

  String generate() {
    return StringTemplate.from(
        """
        ${imports}${construct} ${name}${typeVars} {
        ${properties}
        }

        export default ${name};
        """,
        this);
  }

  public String imports() {
    return tools.getImports();
  }

  public String construct() {
    return entity.type().isEnum() ? "enum" : "interface";
  }

  public String name() {
    return entity.getName();
  }

  public String typeVars() {
    return tools.generateTypeVariables(typeVariables);
  }

  public String properties() {
    return String.join("\n", properties);
  }

  private List<String> generateProperties() {
    if (entity.type().isEnum()) {
      return Arrays.stream(entity.type().getEnumConstants())
          .map(Object::toString)
          .sorted()
          .map(
              value ->
                      """
                \s   %s = "%s","""
                      .formatted(value, value))
          .toList();
    }

    var propertyStream = entity.properties().stream();
    var annotation = entity.type().getAnnotation(JsonIgnoreProperties.class);

    if (!(annotation == null
        || annotation.value() == null
        || annotation.allowGetters()
        || annotation.allowSetters())) {
      var ignoredProperties = Arrays.asList(annotation.value());
      propertyStream = propertyStream.filter(prop -> !ignoredProperties.contains(prop.getName()));
    }

    return propertyStream
        .sorted(Comparator.comparing(BeanPropertyDefinition::getName))
        .map(prop -> new PropertyMaker(tools, prop, typeVariables).generate())
        .toList();
  }
}

package com.cromoteca.generator;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.SortedSet;

class PropertyMaker {
  private final Generator.MakerTools tools;
  private final BeanPropertyDefinition property;
  private final SortedSet<String> typeVariables;

  PropertyMaker(
      Generator.MakerTools tools,
      BeanPropertyDefinition property,
      SortedSet<String> typeVariables) {
    this.tools = tools;
    this.property = property;
    this.typeVariables = typeVariables;
  }

  String generate() {
    var propertyType = MultipleType.forProperty(property);

    return """
      \s   %s: %s;"""
        .formatted(property.getName(), tools.generateType(propertyType, typeVariables));
  }
}

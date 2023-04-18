package com.cromoteca.generator;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class PropertyMaker {
  private final MakerTools tools;
  private final BeanPropertyDefinition property;

  public PropertyMaker(MakerTools tools, BeanPropertyDefinition property) {
    this.tools = tools;
    this.property = property;
  }

  public String generate() {
    var propertyType = MultipleType.forProperty(property);

    return """
      \s   %s: %s;"""
        .formatted(property.getName(), tools.generateType(propertyType));
  }
}

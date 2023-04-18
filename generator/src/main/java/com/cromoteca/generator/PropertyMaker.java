package com.cromoteca.generator;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

class PropertyMaker {
  private final Generator.MakerTools tools;
  private final BeanPropertyDefinition property;

  PropertyMaker(Generator.MakerTools tools, BeanPropertyDefinition property) {
    this.tools = tools;
    this.property = property;
  }

  String generate() {
    var propertyType = MultipleType.forProperty(property);

    return """
      \s   %s: %s;"""
        .formatted(property.getName(), tools.generateType(propertyType));
  }
}

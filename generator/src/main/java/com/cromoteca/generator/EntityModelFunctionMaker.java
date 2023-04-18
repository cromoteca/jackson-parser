package com.cromoteca.generator;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

class EntityModelFunctionMaker {
  private final Generator.MakerTools tools;
  private final BeanPropertyDefinition property;
  private final MultipleType propertyType;
  private final String _getPropertyModel;

  EntityModelFunctionMaker(Generator.MakerTools tools, BeanPropertyDefinition property) {
    this.tools = tools;
    this.property = property;
    propertyType = MultipleType.forProperty(property);
    _getPropertyModel = tools.fromImport("_getPropertyModel", "@hilla/form", false, false);
  }

  String generate() {
    return StringTemplate.from(
        """

        \s   get ${name}(): ${modelType} {
                return this[${getPropertyModel}]('${name}', ${modelType}, [${nullable}]) as ${modelType};
            }""",
        this);
  }

  private String name() {
    return property.getName();
  }

  private String getPropertyModel() {
    return _getPropertyModel;
  }

  private String modelType() {
    return tools.fromImport("StringModel", "@hilla/form", false, false);
  }

  private String nullable() {
    return "false";
  }
}

package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EntityMaker {
  private final MakerTools tools;
  private final ScanResult.EntityClass entity;
  private final List<String> properties;

  public EntityMaker(MakerTools tools, ScanResult.EntityClass entity) {
    this.tools = tools;
    this.entity = entity;
    properties = generateProperties();
  }

  public String generate() {
    return StringTemplate.from(
        """
        ${imports}${construct} ${name}${typeParams} {
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

  public String typeParams() {
    return tools.generateTypeParams(entity.type().getTypeParameters());
  }

  public String properties() {
    return String.join("\n", properties);
  }

  private List<String> generateProperties() {
    if (entity.type().isEnum()) {
      return Arrays.stream(entity.type().getEnumConstants())
          .map(Object::toString)
          .sorted()
          .map(value -> """
                \s   %s = "%s",""".formatted(value, value))
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
        .map(prop -> new PropertyMaker(tools, prop).generate())
        .toList();
  }
}

package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.Comparator;
import java.util.List;

public class EntityModelMaker {
  private final MakerTools tools;
  private final ScanResult.EntityClass entity;
  private final String importedObjectModel;
  private final String importedModelType;
  private final List<String> properties;

  public EntityModelMaker(MakerTools tools, ScanResult.EntityClass entity) {
    this.tools = tools;
    this.entity = entity;
    importedObjectModel = tools.addImport("ObjectModel", "@hilla/form", false, false);
    importedModelType = tools.addImport(entity.getName(), entity.type().getName(), true, true);
    tools.addKeyword("createEmptyValue");
    properties = generateModelFunctions(entity);
  }

  public String generate() {
    return StringTemplate.from(
        """
        ${imports}class ${name}Model<T extends ${name} = ${name}> extends ${objectModel}<T> {
            declare static createEmptyValue: () => ${name};
        ${properties}
        }

        export default ${name}Model;
        """,
        this);
  }

  private List<String> generateModelFunctions(ScanResult.EntityClass entity) {
    return entity.properties().stream()
        .sorted(Comparator.comparing(BeanPropertyDefinition::getName))
        .map(prop -> new EntityModelFunctionMaker(tools, prop).generate())
        .toList();
  }
}

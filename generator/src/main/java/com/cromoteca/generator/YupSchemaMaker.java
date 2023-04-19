package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

class YupSchemaMaker {
  private final Generator.MakerTools tools;
  private final ScanResult.EntityClass entity;
  private final String objectSchema;
  private final String object;
  private final String name;
  private final String type;

  YupSchemaMaker(Generator.MakerTools tools, ScanResult.EntityClass entity) {
    this.tools = tools;
    this.entity = entity;
    objectSchema = tools.fromImport("ObjectSchema", "yup", false, true);
    object = tools.fromImport("object", "yup", false, false);
    name = entity.getName() + "YupSchema";
    type = tools.fromImport(entity.getName(), entity.type().getName(), true, true);
  }

  String generate() {
    return StringTemplate.from(
        """
        ${imports}const ${name}: ${objectSchema}<${type}> = ${object}({
        ${validations}
        });

        export default ${name};
        """,
        this);
  }

  public String getImports() {
    return tools.getImports();
  }

  public String getObjectSchema() {
    return objectSchema;
  }

  public String getObject() {
    return object;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String validations() {
    return entity.properties().stream()
        .sorted()
        .map(
            prop -> {
              var constraints = new HashSet<String>();
              var annotations =
                  Arrays.stream(MultipleType.forProperty(prop).getAnnotations())
                      .collect(Collectors.toMap(Annotation::annotationType, Function.identity()));

              var notBlank = (NotBlank) annotations.get(NotBlank.class);
              if (notBlank != null) {
                constraints.add("required()");
              }
              var email = (Email) annotations.get(Email.class);
              if (email != null) {
                constraints.add("email()");
              }

              return constraints.isEmpty()
                  ? ""
                  : String.format("    %s: %s,", prop.getName(), String.join(".", constraints));
            })
        .collect(Collectors.joining("\n"));
  }
}

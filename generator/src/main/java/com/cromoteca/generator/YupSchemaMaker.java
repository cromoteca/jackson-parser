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

  YupSchemaMaker(Generator.MakerTools tools, ScanResult.EntityClass entity) {
    this.tools = tools;
    this.entity = entity;
  }

  String generate() {
    return StringTemplate.from("""
                ${validationSchema}
                """, this);
  }

  public String validationSchema() {
    return entity.properties().stream()
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
                  : prop.getName() + "." + String.join(".", constraints);
            })
        .collect(Collectors.joining("\n"));
  }
}

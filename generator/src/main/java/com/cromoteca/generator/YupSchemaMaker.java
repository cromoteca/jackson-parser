package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YupSchemaMaker {
  private final MakerTools tools;
  private final ScanResult.EntityClass entity;

  public YupSchemaMaker(MakerTools tools, ScanResult.EntityClass entity) {
    this.tools = tools;
    this.entity = entity;
  }

  public String generate() {
    return StringTemplate.from("""
                ${validationSchema}
                """, this);
  }

  private String validationSchema() {
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

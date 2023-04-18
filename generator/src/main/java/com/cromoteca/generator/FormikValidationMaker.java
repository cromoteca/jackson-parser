package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

class FormikValidationMaker {
  private final Generator.MakerTools tools;
  private final ScanResult.EntityClass entity;
  private final ResourceBundle bundle;

  FormikValidationMaker(Generator.MakerTools tools, ScanResult.EntityClass entity, Locale locale) {
    this.tools = tools;
    this.entity = entity;
    bundle = ResourceBundle.getBundle("org.hibernate.validator.ValidationMessages");
  }

  String generate() {
    return StringTemplate.from(
        """
        const emptyValue: ${className} = {
          ${emptyValues}
        };

        const validationSchema: ObjectSchema<${className}> = object({
          ${validationSchema}
        });

        export { emptyValue, validationSchema };
        """,
        this);
  }

  private String emptyValues() {
    return entity.properties().stream()
        .map(prop -> tools.handlerFor(prop.getRawPrimaryType()).emptyValue())
        .collect(Collectors.joining(", "));
  }

  private String generateValidations(ScanResult.EntityClass entity) {
    var stringBuilder = new StringBuilder();
    entity
        .properties()
        .forEach(
            prop -> {
              var annotations =
                  Arrays.stream(MultipleType.forProperty(prop).getAnnotations())
                      .collect(Collectors.toMap(Annotation::annotationType, Function.identity()));

              var notBlank = (NotBlank) annotations.get(NotBlank.class);
              if (notBlank != null) {
                stringBuilder.append(
                    StringTemplate.from(
                        """
                        \s if (!errors.${name} && !values.${name}) {
                              errors.${name} = `${message}`;
                          }
                        """,
                        Map.of(
                            "name",
                            prop.getName(),
                            "message",
                            bundle.getString(notBlank.message()))));
              }

              var email = (Email) annotations.get(Email.class);
              if (email != null) {
                stringBuilder.append(
                    // FIXME implement better email validation
                    StringTemplate.from(
                        """
                        \s if (!errors.${name} && !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,9}$/i.test(values.${name})) {
                              errors.${name} = `${message}`;
                          }
                        """,
                        Map.of(
                            "name", prop.getName(), "message", bundle.getString(email.message()))));
              }

              var pattern =
                  (jakarta.validation.constraints.Pattern)
                      annotations.get(jakarta.validation.constraints.Pattern.class);
              if (pattern != null) {
                stringBuilder.append(
                    // FIXME implement better email validation
                    StringTemplate.from(
                        """
                        \s if (!errors.${name} && !/${regexp}/.test(values.${name})) {
                              errors.${name} = `${message}`;
                          }
                        """,
                        Map.of(
                            "name",
                            prop.getName(),
                            "regexp",
                            pattern.regexp(),
                            "message",
                            bundle.getString(pattern.message()))));
              }
            });
    return stringBuilder.toString();
  }
}

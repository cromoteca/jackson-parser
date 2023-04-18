package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import java.util.stream.Collectors;

class FormikValidationMaker {
  private final Generator.MakerTools tools;
  private final ScanResult.EntityClass entity;

  FormikValidationMaker(Generator.MakerTools tools, ScanResult.EntityClass entity) {
    this.tools = tools;
    this.entity = entity;
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
}

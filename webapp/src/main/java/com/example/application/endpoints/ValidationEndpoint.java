package com.example.application.endpoints;

import com.cromoteca.samples.endpoints.FormValidationEndpoint;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import jakarta.validation.Validation;
import java.util.List;

@Endpoint
@AnonymousAllowed
public class ValidationEndpoint {
  public @Nonnull List<@Nonnull Error> validate(
      FormValidationEndpoint.@Nonnull RegistrationInfo values) {
    var factory = Validation.buildDefaultValidatorFactory();
    var validator = factory.getValidator();

    return validator.validate(values).stream()
        .map(violation -> new Error(violation.getPropertyPath().toString(), violation.getMessage()))
        .toList();
  }

  public record Error(@Nonnull String field, @Nonnull String message) {}

  public void preValidate(FormValidationEndpoint.@Nonnull RegistrationInfo values) {}
}

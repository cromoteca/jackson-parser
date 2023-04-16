package com.cromoteca.samples.endpoints;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Endpoint
@AnonymousAllowed
public class FormValidationEndpoint {

  @Nonnull
  public String validate(@Nonnull RegistrationInfo info) {
    return "Registration accepted";
  }

  @Valid
  public static record RegistrationInfo(
      @NotBlank String name,
      @NotBlank @Email String email,
      @Pattern(regexp = "^[0-9]+$") String phone,
      @Size(min = 2, max = 2) String country,
      @AssertTrue boolean conditions) {}
}

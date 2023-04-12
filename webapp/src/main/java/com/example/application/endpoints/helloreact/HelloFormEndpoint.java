package com.example.application.endpoints.helloreact;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Endpoint
@AnonymousAllowed
public class HelloFormEndpoint {

  public String validate(@Nonnull RegistrationInfo info) {
    if (info.email().contains("gmail.com")) {
      return "Gmail is not allowed";
    }

    return "OK";
  }

  public static record RegistrationInfo(
      @NotBlank String name,
      @NotBlank @Email String email,
      @Pattern(regexp = "^[0-9]+$") String phone,
      @Size(min = 2, max = 2) String country) {}
}

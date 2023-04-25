package com.cromoteca.samples.endpoints;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import org.springframework.lang.Nullable;

@Endpoint
@AnonymousAllowed
public class FormValidationEndpoint {

  @Nonnull
  public String handleRegistration(@Nonnull RegistrationInfo info) {
    return "Registration accepted";
  }

  /** This annotation is used to demonstrate server-side custom validation. */
  @Documented
  @Constraint(validatedBy = NotUsedBeforeValidator.class)
  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface NotUsedBefore {
    String message() default "Already exists in our database";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
  }

  /** This validator is used to validate that the email or phone number is not already used. */
  public static class NotUsedBeforeValidator implements ConstraintValidator<NotUsedBefore, String> {
    private static final List<String> USED_EMAILS = List.of("john.doe@example.com");
    private static final List<String> USED_PHONES = List.of("0123456789");

    public void initialize(NotUsedBefore constraintAnnotation) {}

    public boolean isValid(String value, ConstraintValidatorContext context) {
      return value == null || !(USED_EMAILS.contains(value) || USED_PHONES.contains(value));
    }
  }

  /**
   * This record is used to validate the registration form.
   *
   * <p>It uses the {@link NotUsedBefore} annotation.
   */
  @Valid
  public static record RegistrationInfo(
      @NotBlank String name,
      @NotBlank @Email @NotUsedBefore String email,
      @Nullable @Pattern(regexp = "^[0-9]*$") @NotUsedBefore String phone,
      @Size(min = 2, max = 3) String country,
      @AssertTrue boolean terms) {}
}

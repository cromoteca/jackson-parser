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

  /**
   * This method is used to handle the registration form.
   *
   * <p>It uses the {@link RegistrationInfo} record.
   */
  @Nonnull
  public String handleRegistration(@Nonnull RegistrationInfo info) {
    return "Registration accepted";
  }

  /**
   * This method is used to pre-validate the registration form.
   *
   * <p>It does nothing, as the goal here is to take advantage of the validation which happens
   * before invoking this method.
   */
  public void preValidate(@Nonnull RegistrationInfo info) {}

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
      @NotBlank(message = "Name is required") String name,
      @NotBlank(message = "Email is required")
          @Email(message = "Email must be valid")
          @NotUsedBefore
          String email,
      @Nullable @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone must be valid") @NotUsedBefore
          String phone,
      @NotBlank(message = "Country is required")
          @Size(min = 2, max = 3, message = "Country code must be 2 or 3 characters")
          String country,
      @AssertTrue(message = "You must accept the terms and conditions") boolean terms) {}
}

package com.cromoteca.samples.nullable.endpoints;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import java.util.List;
import java.util.Map;

@Endpoint
@AnonymousAllowed
public class NullableEndpoint {
  public Integer box(int n) {
    return n;
  }

  @Nonnull
  public String notNull() {
    return "";
  }

  public void nulls(Nulls argument) {}

  public void nonNullListOfNonNullStrings(@Nonnull List<@Nonnull String> argument) {}

  public void nullableArrayOfNullableStrings(String[] argument) {}

  public void nullableArrayOfNonNullStrings(@Nonnull String[] argument) {}

  public void nonNullArrayOfNullableStrings(String @Nonnull [] argument) {}

  public void nonNullArrayOfNonNullStrings(@Nonnull String @Nonnull [] argument) {}

  public static class Nulls {
    private @Nonnull Map<@Nonnull String, @Nonnull List<@Nonnull String>> allNull;
    private @Nonnull Map<@Nonnull String, @Nonnull List<String>> someNull;
    private @Nonnull List<@Nonnull Object> nullOrNot;

    public Map<String, List<String>> getAllNull() {
      return allNull;
    }

    public void setAllNull(@Nonnull Map<String, List<String>> allNull) {
      this.allNull = allNull;
    }

    public Map<String, List<String>> getSomeNull() {
      return someNull;
    }

    public void setSomeNull(Map<String, List<String>> someNull) {
      this.someNull = someNull;
    }

    // The type argument is nullable and cannot inherit the annotation since the type argument of
    // the field is different.
    public @Nonnull List<String> getNullOrNot() {
      return nullOrNot.stream().map(Object::toString).toList();
    }
  }
}

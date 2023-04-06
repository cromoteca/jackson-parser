package com.cromoteca.generator.nullability;

import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import java.util.List;

@Endpoint
public class BasicEndpoint {
  public Integer box(int n) {
    return n;
  }

  @Nonnull
  public String notNull() {
    return "";
  }

  public void nonNullListOfNonNullStrings(@Nonnull List<@Nonnull String> argument) {}
}

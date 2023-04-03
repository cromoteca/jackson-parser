package dev.hilla.generator.nullability;

import dev.hilla.parser.annotations.Endpoint;
import dev.hilla.parser.annotations.Nonnull;
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

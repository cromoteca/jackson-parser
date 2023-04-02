package dev.hilla.generator.nullability;

import dev.hilla.parser.annotations.Endpoint;

@Endpoint
public class BasicEndpoint {
  public Integer box(int n) {
    return n;
  }
}

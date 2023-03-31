package dev.hilla.generator.example;

import dev.hilla.parser.annotations.Endpoint;

@Endpoint
public class BasicEndpoint {
  @ShouldBeGenerated
  public String simpleMethod(String argument) {
    return argument;
  }
}

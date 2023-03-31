package dev.hilla.generator.example;

import dev.hilla.parser.annotations.Endpoint;
import java.util.List;

@Endpoint
public class BasicEndpoint {
  public String fromStringToString(String argument) {
    return argument;
  }

  public List<String> fromNothingToListOfStrings() {
    return List.of();
  }
}

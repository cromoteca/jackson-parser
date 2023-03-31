package dev.hilla.generator.example;

import dev.hilla.parser.annotations.Endpoint;
import java.util.List;
import java.util.Map;

@Endpoint
public class BasicEndpoint {
  public String fromStringToString(String argument) {
    return argument;
  }

  public List<String> fromNothingToListOfStrings() {
    return List.of();
  }

  public void fromArrayOfStringsToNothing(String[] argument) {}

  public int[] fromPrimitiveToPrimitiveArray(int argument) {
    return new int[] {argument};
  }

  public Map<String, List<String>> fromTwoToMapOfStringsToList(String init, String end) {
    return Map.of();
  }
}

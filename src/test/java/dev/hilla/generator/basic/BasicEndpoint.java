package dev.hilla.generator.basic;

import dev.hilla.parser.annotations.Endpoint;
import java.util.List;
import java.util.Map;

@Endpoint
public class BasicEndpoint {
  public void fromArrayOfStringsToNothing(String[] argument) {}

  public List<String> fromNothingToListOfStrings() {
    return List.of();
  }

  public int[] fromPrimitiveToPrimitiveArray(int argument) {
    return new int[] {argument};
  }

  public String fromStringToString(String argument) {
    return argument;
  }

  public Map<String, List<String>> fromTwoToMapOfStringsToList(String init, String end) {
    return Map.of();
  }
}

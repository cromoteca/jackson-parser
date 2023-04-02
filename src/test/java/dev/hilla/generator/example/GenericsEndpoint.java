package dev.hilla.generator.example;

import dev.hilla.parser.annotations.Endpoint;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Endpoint
public class GenericsEndpoint {
  public List<GenericClass<String>> toListOfMyType(GenericClass<String> value) {
    return List.of(value);
  }

  public <T> List<T> toList(T[] value) {
    return Arrays.asList(value);
  }

  public <T, U> Map<T, GenericClass<U>> toMap(T key, GenericClass<U> value) {
    return Map.of(key, value);
  }

  public <T> GenericClass<GenericClass<T>> toNestedGenericClass(GenericClass<T> value) {
    return new GenericClass<>();
  }

  public <T, U, V>
      Map<GenericClass<List<T>>, Map<?, GenericClass<GenericClass<GenericClass<V>>>>> tooComplex(
          GenericClass<List<?>> p1, Map<U, GenericClass<GenericClass<GenericClass<?>>>> p2) {
    return null;
  }

  public static class GenericClass<T> {
    public T value;
  }
}

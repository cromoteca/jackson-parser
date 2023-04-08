package com.cromoteca.samples.endpoints;

import dev.hilla.Endpoint;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    return Map.of();
  }

  public Optional<String> opt() {
    return Optional.empty();
  }

  public List<Optional<String>> optList() {
    return List.of(Optional.empty());
  }

  public <T> List<T> optListT(List<Optional<T>> argument) {
    return List.of();
  }

  public static class GenericClass<T> {
    public T value;
  }
}

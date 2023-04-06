package dev.hilla.parser.example;

import static dev.hilla.parser.example.BasicEntities.*;

import dev.hilla.EndpointExposed;
import java.util.Collection;

@EndpointExposed
public class ExposedSuperclass<T, U> {
  @ShouldBeParsed
  public ReturnTypeInExposedMethod publicMethodInExposedSuperclass(
      ParameterTypeInExposedMethod parameterTypeInExposedMethod) {
    return new ReturnTypeInExposedMethod();
  }

  public ReturnTypeInNotExposedMethod publicMethodOverriddenInNotExposedSuperclass(
      ParameterTypeInNotExposedMethod parameterTypeInNotExposedMethod) {
    return new ReturnTypeInNotExposedMethod();
  }

  @ShouldBeParsed
  public T getBackParameterizedType(T object) {
    return object;
  }

  public int collectionSize(Collection<U> collection) {
    return collection.size();
  }
}

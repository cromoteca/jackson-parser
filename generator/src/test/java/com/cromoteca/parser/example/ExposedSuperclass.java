package com.cromoteca.parser.example;

import static com.cromoteca.parser.example.BasicEntities.*;

import com.vaadin.hilla.EndpointExposed;
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

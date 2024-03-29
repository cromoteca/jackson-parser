package com.cromoteca.parser.example;

import static com.cromoteca.parser.example.BasicEntities.*;

public class NotExposedSuperclass<T> extends ExposedSuperclass<T, String> {
  public ReturnTypeInNotExposedMethod publicMethodInNotExposedSuperclass(
      ParameterTypeInNotExposedMethod parameterTypeInNotExposedMethod) {
    return new ReturnTypeInNotExposedMethod();
  }

  @Override
  public ReturnTypeInNotExposedMethod publicMethodOverriddenInNotExposedSuperclass(
      ParameterTypeInNotExposedMethod parameterTypeInNotExposedMethod) {
    return new ReturnTypeInNotExposedMethod();
  }

  public ReturnTypeInExposedMethod publicMethodOverriddenInEndpoint(
      ParameterTypeInExposedMethod parameterTypeInExposedMethod) {
    return new ReturnTypeInExposedMethod();
  }
}

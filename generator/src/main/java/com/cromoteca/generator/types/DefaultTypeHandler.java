package com.cromoteca.generator.types;

public class DefaultTypeHandler {
  public boolean isSupported(Class<?> cls) {
    return true;
  }

  public String parameterType(Class<?> type) {
    return type.getName();
  }

  public String modelType(Class<?> type) {
    return type.getName() + "Model";
  }

  public boolean canHaveGenerics() {
    return true;
  }

  public EndpointMethodType endpointMethodType() {
    return EndpointMethodType.CALL;
  }

  public boolean generateEntityProperties() {
    return true;
  }

  public String emptyValue() {
    return "undefined";
  }

  public enum EndpointMethodType {
    CALL,
    SUBSCRIBE
  }
}

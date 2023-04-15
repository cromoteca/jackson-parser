package com.cromoteca.generator.types;

import java.util.Set;

public class TypeHandler {
  public Set<Class<?>> supportedTypes() {
    return Set.of();
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

  public boolean generateEntity() {
    return true;
  }

  /**
   * Type representation when the class is used as method return type. By default, it is the same as
   * parameter type.
   */
  public String returnType(Class<?> cls) {
    return parameterType(cls);
  }

  public enum EndpointMethodType {
    CALL,
    SUBSCRIBE
  }
}

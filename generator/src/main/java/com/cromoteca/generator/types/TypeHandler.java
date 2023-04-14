package com.cromoteca.generator.types;

import java.util.Set;

public class TypeHandler {
  public Set<Class<?>> supportedTypes() {
    return Set.of();
  }

  public String generateType(Class<?> type) {
    return type.getName();
  }

  public String generateModelType(Class<?> type) {
    return type.getSimpleName() + "Model";
  }

  public boolean canHaveGenerics() {
    return true;
  }

  public MethodType methodType() {
    return MethodType.CALL;
  }

  public enum MethodType {
    CALL,
    SUBSCRIBE
  }
}

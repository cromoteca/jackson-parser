package com.cromoteca.generator.types;

import java.util.Set;

public class UnknownTypeHandler extends TypeHandler {

  @Override
  public Set<Class<?>> supportedTypes() {
    return Set.of(Object.class);
  }

  @Override
  public String parameterType(Class<?> type) {
    return "unknown";
  }

  @Override
  public String modelType(Class<?> type) {
    return "ObjectModel";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }
}

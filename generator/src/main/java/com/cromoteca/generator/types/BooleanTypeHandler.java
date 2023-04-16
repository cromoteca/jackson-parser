package com.cromoteca.generator.types;

import java.util.Set;

public class BooleanTypeHandler extends TypeHandler {

  @Override
  public Set<Class<?>> supportedTypes() {
    return Set.of(Boolean.class, boolean.class);
  }

  @Override
  public String parameterType(Class<?> type) {
    return "boolean";
  }

  @Override
  public String modelType(Class<?> type) {
    return "BooleanModel";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }

  @Override
  public String emptyValue() {
    return "false";
  }
}

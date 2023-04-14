package com.cromoteca.generator.types;

import java.util.Set;

public class BooleanTypeHandler extends TypeHandler {

  @Override
  public Set<Class<?>> supportedTypes() {
    return Set.of(Boolean.class, boolean.class);
  }

  @Override
  public String generateType(Class<?> type) {
    return "boolean";
  }

  @Override
  public String generateModelType(Class<?> type) {
    return "BooleanModel";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }
}

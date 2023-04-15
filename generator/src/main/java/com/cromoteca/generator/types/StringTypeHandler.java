package com.cromoteca.generator.types;

import java.util.Set;

public class StringTypeHandler extends TypeHandler {

  @Override
  public Set<Class<?>> supportedTypes() {
    return Set.of(String.class, CharSequence.class, char.class, Character.class);
  }

  @Override
  public String parameterType(Class<?> type) {
    return "string";
  }

  @Override
  public String modelType(Class<?> type) {
    return "StringModel";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }
}

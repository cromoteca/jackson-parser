package com.cromoteca.generator.types;

import java.util.Set;

public class StringTypeHandler extends DefaultTypeHandler {

  public static final Set<Class<?>> SUPPORTED =
      Set.of(String.class, CharSequence.class, char.class, Character.class);

  public boolean isSupported(Class<?> cls) {
    return SUPPORTED.contains(cls);
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

  @Override
  public String emptyValue() {
    return "''";
  }
}

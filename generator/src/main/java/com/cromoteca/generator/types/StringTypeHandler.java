package com.cromoteca.generator.types;

import java.util.Set;

public class StringTypeHandler extends TypeHandler {

  @Override
  public Set<Class<?>> supportedTypes() {
    return Set.of(String.class, CharSequence.class, char.class, Character.class);
  }

  @Override
  public String generateType(Class<?> type) {
    return "string";
  }

  @Override
  public String generateModelType(Class<?> type) {
    return "StringModel";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }
}

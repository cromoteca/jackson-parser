package com.cromoteca.generator.types;

import java.util.Set;

public class NumberTypeHandler extends TypeHandler {

  @Override
  public Set<Class<?>> supportedTypes() {
    return Set.of(
        Byte.class,
        Short.class,
        Integer.class,
        Long.class,
        Float.class,
        Double.class,
        byte.class,
        short.class,
        int.class,
        long.class,
        float.class,
        double.class);
  }

  @Override
  public String parameterType(Class<?> type) {
    return "number";
  }

  @Override
  public String modelType(Class<?> type) {
    return "NumberModel";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }

  @Override
  public String emptyValue() {
    return "0";
  }
}

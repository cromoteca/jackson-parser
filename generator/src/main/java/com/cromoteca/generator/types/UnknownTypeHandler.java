package com.cromoteca.generator.types;

public class UnknownTypeHandler extends DefaultTypeHandler {

  public boolean isSupported(Class<?> cls) {
    return cls.getName().startsWith("java.");
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

package com.cromoteca.generator;

import com.cromoteca.generator.types.TypeHandler;
import java.lang.reflect.TypeVariable;

public interface MakerTools {
  String generateType(FullType type);

  String addImport(String variable, String from, boolean isDefault, boolean isType);

  TypeHandler handlerFor(Class<?> cls);

  String generateTypeParams(TypeVariable<?>[] typeParameters);

  String getImports();
}

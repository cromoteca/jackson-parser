package com.cromoteca.generator;

import com.cromoteca.generator.types.TypeHandler;
import java.lang.reflect.TypeVariable;
import java.util.Objects;
import java.util.stream.Stream;
import org.springframework.lang.NonNullApi;

public interface MakerTools {
  String generateType(FullType type);

  String fromImport(String variable, String from, boolean isDefault, boolean isType);

  TypeHandler handlerFor(Class<?> cls);

  String generateTypeParams(TypeVariable<?>[] typeParameters);

  String getImports();

  void addKeyword(String variable);

  static boolean insideNonNullApi(Class<?> cls) {
    var classLoader = cls.getClassLoader();

    return Stream.iterate(
            cls.getPackageName(), n -> n.contains("."), n -> n.substring(0, n.lastIndexOf('.')))
        .map(classLoader::getDefinedPackage)
        .filter(Objects::nonNull)
        .anyMatch(pkg -> pkg.isAnnotationPresent(NonNullApi.class));
  }
}

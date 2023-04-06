package com.cromoteca.generator;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class FullType {
  private final JavaType _type;
  private final AnnotatedType _generic;

  FullType(JavaType type, AnnotatedType generic) {
    _type = type;
    _generic = generic;
  }

  static <T> Optional<T> castIfPossible(Object object, Class<T> cls) {
    if (object instanceof Optional<?> optional) {
      return optional.map(o -> cls.isInstance(o) ? cls.cast(o) : null);
    }
    return cls.isInstance(object) ? Optional.of(cls.cast(object)) : Optional.empty();
  }

  boolean isOptional() {
    return _type.hasRawClass(Optional.class);
  }

  boolean isArrayType() {
    return _type.isArrayType();
  }

  boolean isPrimitive() {
    return _type.isPrimitive();
  }

  boolean isCollectionLikeType() {
    return _type.isCollectionLikeType();
  }

  boolean isMapLikeType() {
    return _type.isMapLikeType();
  }

  FullType[] getMapTypes() {
    var itemTypes =
        castIfPossible(_generic, AnnotatedParameterizedType.class)
            .map(AnnotatedParameterizedType::getAnnotatedActualTypeArguments);
    return new FullType[] {
      new FullType(_type.getKeyType(), itemTypes.map(i -> i[0]).orElse(null)),
      new FullType(_type.getContentType(), itemTypes.map(i -> i[1]).orElse(null))
    };
  }

  FullType getBoundType() {
    var itemType =
        castIfPossible(_generic, AnnotatedParameterizedType.class)
            .map(p -> p.getAnnotatedActualTypeArguments()[0]);
    return new FullType(_type.getBindings().getBoundType(0), itemType.orElse(null));
  }

  FullType getContentType() {
    var itemType =
        castIfPossible(_generic, AnnotatedParameterizedType.class)
            .map(p -> p.getAnnotatedActualTypeArguments()[0]);
    return new FullType(_type.getContentType(), itemType.orElse(null));
  }

  FullType getArrayType() {
    var itemType =
        castIfPossible(_generic, AnnotatedArrayType.class)
            .map(AnnotatedArrayType::getAnnotatedGenericComponentType);
    return new FullType(_type.getContentType(), itemType.orElse(null));
  }

  Class<?> getRawClass() {
    return _type.getRawClass();
  }

  Boolean nullable() {
    return Optional.ofNullable(_generic)
        .map(
            g -> {
              Boolean value = null;

              for (var annotation : g.getAnnotations()) {
                var name = annotation.annotationType().getSimpleName().toLowerCase();

                if (name.matches("no[nt]null")) {
                  value = false;
                  break;
                } else if (name.contains("nullable")) {
                  value = true;
                }
              }

              return value;
            })
        .orElse(null);
  }

  String rawTypeName() {
    var rawTypeFromGeneric =
        Optional.ofNullable(_generic)
            .flatMap(g -> castIfPossible(g, TypeVariable.class))
            .map(TypeVariable::getName);
    return rawTypeFromGeneric.orElse(_type.getRawClass().getName());
  }

  Optional<String> typeVariableName() {
    return Optional.ofNullable(_generic)
        .flatMap(g -> castIfPossible(g, AnnotatedTypeVariable.class))
        .map(AnnotatedTypeVariable::getType)
        .map(Type::getTypeName);
  }

  Optional<Stream<FullType>> parameterizedTypes() {
    return Optional.ofNullable(_generic)
        .flatMap(g -> castIfPossible(g, AnnotatedParameterizedType.class))
        .map(AnnotatedParameterizedType::getAnnotatedActualTypeArguments)
        .map(
            types ->
                IntStream.range(0, types.length)
                    .mapToObj(i -> new FullType(_type.getBindings().getBoundType(i), types[i])));
  }
}

package com.cromoteca.generator;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class FullType {
  private final JavaType javaType;
  private final AnnotatedType annotatedType;
  private final AnnotatedElement annotatedElement;

  FullType(JavaType javaType, AnnotatedType annotatedType, AnnotatedElement annotatedElement) {
    this.javaType = javaType;
    this.annotatedType = annotatedType;
    this.annotatedElement = annotatedElement;
  }

  static <T> Optional<T> castIfPossible(Object object, Class<T> cls) {
    if (object instanceof Optional<?> optional) {
      return optional.filter(cls::isInstance).map(cls::cast);
    } else {
      return Optional.ofNullable(object).filter(cls::isInstance).map(cls::cast);
    }
  }

  boolean isOptional() {
    return javaType.hasRawClass(Optional.class);
  }

  boolean isArrayType() {
    return javaType.isArrayType();
  }

  boolean isPrimitive() {
    return javaType.isPrimitive();
  }

  boolean isCollectionLikeType() {
    return javaType.isCollectionLikeType();
  }

  boolean isMapLikeType() {
    return javaType.isMapLikeType();
  }

  FullType[] getMapTypes() {
    var annotatedTypes =
        castIfPossible(annotatedType, AnnotatedParameterizedType.class)
            .map(AnnotatedParameterizedType::getAnnotatedActualTypeArguments);
    // TODO: map annotatedElement
    return new FullType[] {
      new FullType(javaType.getKeyType(), annotatedTypes.map(i -> i[0]).orElse(null), null),
      new FullType(javaType.getContentType(), annotatedTypes.map(i -> i[1]).orElse(null), null)
    };
  }

  FullType getBoundType() {
    var itemType =
        castIfPossible(annotatedType, AnnotatedParameterizedType.class)
            .map(p -> p.getAnnotatedActualTypeArguments()[0]);
    return new FullType(javaType.getBindings().getBoundType(0), itemType.orElse(null), null);
  }

  FullType getContentType() {
    var itemType =
        castIfPossible(annotatedType, AnnotatedParameterizedType.class)
            .map(p -> p.getAnnotatedActualTypeArguments()[0]);
    return new FullType(javaType.getContentType(), itemType.orElse(null), null);
  }

  FullType getArrayType() {
    var itemType =
        castIfPossible(annotatedType, AnnotatedArrayType.class)
            .map(AnnotatedArrayType::getAnnotatedGenericComponentType);
    return new FullType(javaType.getContentType(), itemType.orElse(null), null);
  }

  Class<?> getRawClass() {
    return javaType.getRawClass();
  }

  Boolean nullable() {
    var annotations = getAnnotations();
    Boolean value = null;

    for (var annotation : annotations) {
      var name = annotation.annotationType().getSimpleName().toLowerCase();

      if (name.matches("no[nt]null")) {
        value = false;
        break;
      } else if (name.contains("nullable")) {
        value = true;
      }
    }

    return value;
  }

  Annotation[] getAnnotations() {
    return Stream.of(annotatedType, annotatedElement)
        .filter(Objects::nonNull)
        .flatMap(a -> Arrays.stream(a.getAnnotations()))
        .distinct()
        .toArray(Annotation[]::new);
  }

  String rawTypeName() {
    var rawTypeFromGeneric =
        Optional.ofNullable(annotatedType)
            .flatMap(g -> castIfPossible(g, TypeVariable.class))
            .map(TypeVariable::getName);
    return rawTypeFromGeneric.orElse(javaType.getRawClass().getName());
  }

  Optional<String> typeVariableName() {
    return Optional.ofNullable(annotatedType)
        .flatMap(g -> castIfPossible(g, AnnotatedTypeVariable.class))
        .map(AnnotatedTypeVariable::getType)
        .map(Type::getTypeName);
  }

  Optional<Stream<FullType>> parameterizedTypes() {
    return Optional.ofNullable(annotatedType)
        .flatMap(g -> castIfPossible(g, AnnotatedParameterizedType.class))
        .map(AnnotatedParameterizedType::getAnnotatedActualTypeArguments)
        .map(
            types ->
                IntStream.range(0, types.length)
                    .mapToObj(
                        i -> new FullType(javaType.getBindings().getBoundType(i), types[i], null)));
  }
}

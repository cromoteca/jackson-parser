package com.cromoteca.generator;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FullType {
  private final JavaType javaType;
  private final AnnotatedElement[] annotatedElements;

  public FullType(JavaType javaType, AnnotatedElement... annotatedElements) {
    this.javaType = javaType;
    this.annotatedElements = annotatedElements;
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

  private List<AnnotatedType[]> extractAnnotatedActualTypeArguments(int expectedCount) {
    var annotatedTypes =
        Arrays.stream(annotatedElements)
            .map(e -> castIfPossible(e, AnnotatedParameterizedType.class))
            .flatMap(Optional::stream)
            .map(AnnotatedParameterizedType::getAnnotatedActualTypeArguments)
            .filter(a -> a.length == expectedCount)
            .toList();
    return IntStream.range(0, expectedCount)
        .mapToObj(n -> annotatedTypes.stream().map(a -> a[n]).toArray(AnnotatedType[]::new))
        .toList();
  }

  FullType[] getMapTypes() {
    var extracted = extractAnnotatedActualTypeArguments(2);

    return new FullType[] {
      new FullType(javaType.getKeyType(), extracted.get(0)),
      new FullType(javaType.getContentType(), extracted.get(1))
    };
  }

  FullType getBoundType() {
    return new FullType(
        javaType.getBindings().getBoundType(0), extractAnnotatedActualTypeArguments(1).get(0));
  }

  FullType getContentType() {
    return new FullType(javaType.getContentType(), extractAnnotatedActualTypeArguments(1).get(0));
  }

  FullType getArrayType() {
    var annotatedTypes =
        Arrays.stream(annotatedElements)
            .map(e -> castIfPossible(e, AnnotatedArrayType.class))
            .flatMap(Optional::stream)
            .map(AnnotatedArrayType::getAnnotatedGenericComponentType)
            .toArray(AnnotatedElement[]::new);

    return new FullType(javaType.getContentType(), annotatedTypes);
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
    return Arrays.stream(annotatedElements)
        .filter(Objects::nonNull)
        .flatMap(a -> Arrays.stream(a.getAnnotations()))
        .distinct()
        .toArray(Annotation[]::new);
  }

  String rawTypeName() {
    return javaType.getRawClass().getName();
  }

  Optional<String> typeVariableName() {
    return Arrays.stream(annotatedElements)
        .map(e -> castIfPossible(e, AnnotatedTypeVariable.class))
        .flatMap(Optional::stream)
        .map(AnnotatedTypeVariable::getType)
        .map(Type::getTypeName)
        .findFirst();
  }

  Optional<Stream<FullType>> parameterizedTypes() {
    return Arrays.stream(annotatedElements)
        .map(e -> castIfPossible(e, AnnotatedParameterizedType.class))
        .flatMap(Optional::stream)
        .map(AnnotatedParameterizedType::getAnnotatedActualTypeArguments)
        .map(
            types ->
                IntStream.range(0, types.length)
                    .mapToObj(
                        i -> new FullType(javaType.getBindings().getBoundType(i), types[i], null)))
        .findFirst();
  }
}

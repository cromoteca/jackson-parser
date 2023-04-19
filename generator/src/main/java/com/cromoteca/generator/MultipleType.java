package com.cromoteca.generator;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class MultipleType extends FullType {
  private final List<FullType> types;
  private final FullType mainType;

  MultipleType(List<FullType> types) {
    super(null);
    mainType = types.get(0);
    this.types =
        types.stream().filter(t -> mainType.getRawClass().equals(t.getRawClass())).toList();
  }

  public static MultipleType forProperty(BeanPropertyDefinition property) {
    var getterType =
        Optional.ofNullable(property.getGetter())
            .map(
                getter ->
                    new FullType(
                        getter.getType(),
                        getter.getAnnotated().getAnnotatedReturnType(),
                        getter.getAnnotated().getReturnType()));
    var setterType =
        Optional.ofNullable(property.getSetter())
            .map(
                setter ->
                    new FullType(
                        setter.getParameterType(0),
                        setter.getAnnotated().getAnnotatedParameterTypes()[0],
                        setter.getAnnotated().getParameterTypes()[0]));
    var fieldType =
        Optional.ofNullable(property.getField())
            .map(
                field ->
                    new FullType(
                        field.getType(),
                        field.getAnnotated().getAnnotatedType(),
                        field.getAnnotated()));
    return new MultipleType(
        Stream.of(getterType, setterType, fieldType).flatMap(Optional::stream).toList());
  }

  @Override
  boolean isOptional() {
    return mainType.isOptional();
  }

  @Override
  boolean isArrayType() {
    return mainType.isArrayType();
  }

  @Override
  boolean isPrimitive() {
    return mainType.isPrimitive();
  }

  @Override
  boolean isCollectionLikeType() {
    return mainType.isCollectionLikeType();
  }

  @Override
  boolean isMapLikeType() {
    return mainType.isMapLikeType();
  }

  @Override
  MultipleType[] getMapTypes() {
    var keyTypes = new ArrayList<FullType>();
    var valueTypes = new ArrayList<FullType>();

    types.stream()
        .map(FullType::getMapTypes)
        .flatMap(Arrays::stream)
        .forEach(
            type -> {
              if (keyTypes.size() > valueTypes.size()) {
                valueTypes.add(type);
              } else {
                keyTypes.add(type);
              }
            });

    return new MultipleType[] {new MultipleType(keyTypes), new MultipleType(valueTypes)};
  }

  @Override
  MultipleType getBoundType() {
    return new MultipleType(types.stream().map(FullType::getBoundType).toList());
  }

  @Override
  FullType getContentType() {
    return new MultipleType(types.stream().map(FullType::getContentType).toList());
  }

  @Override
  FullType getArrayType() {
    return new MultipleType(types.stream().map(FullType::getArrayType).toList());
  }

  @Override
  Class<?> getRawClass() {
    return mainType.getRawClass();
  }

  @Override
  Annotation[] getAnnotations() {
    return types.stream()
        .flatMap(t -> Arrays.stream(t.getAnnotations()))
        .distinct()
        .toArray(Annotation[]::new);
  }

  @Override
  String rawTypeName() {
    return mainType.rawTypeName();
  }

  @Override
  String typeVariableName() {
    return mainType.typeVariableName();
  }

  @Override
  Optional<Stream<FullType>> parameterizedTypes() {
    return mainType.parameterizedTypes();
  }
}

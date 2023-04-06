package com.cromoteca.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

class MultipleType extends FullType {
  private final List<FullType> types;
  private final FullType mainType;

  MultipleType(List<FullType> types) {
    super(null, null);
    mainType = types.get(0);
    this.types =
        types.stream().filter(t -> mainType.getRawClass().equals(t.getRawClass())).toList();
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
  Boolean nullable() {
    return types.stream().map(FullType::nullable).filter(Objects::nonNull).findFirst().orElse(null);
  }

  @Override
  String rawTypeName() {
    return mainType.rawTypeName();
  }

  @Override
  Optional<String> typeVariableName() {
    return mainType.typeVariableName();
  }

  @Override
  Optional<Stream<FullType>> parameterizedTypes() {
    return mainType.parameterizedTypes();
  }
}

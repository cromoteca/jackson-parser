package com.cromoteca.parser.example;

import dev.hilla.Endpoint;
import jakarta.annotation.Nullable;
import java.util.Collection;
import java.util.List;

@Endpoint
public class BasicEndpoint extends NotExposedSuperclass<String> {
  @ShouldBeParsed
  public String simpleMethod(String argument) {
    return argument;
  }

  @ShouldBeParsed
  public List<BasicEntities.ListItemInMethodReturnType> listItemInMethodReturnTypes() {
    return List.of(
        new BasicEntities.ListItemInMethodReturnType(
            List.of(new BasicEntities.ListItemInRecordParameter())));
  }

  @ShouldBeParsed
  public void setItemsInMethodParameter(
      BasicEntities.ArrayItemInMethodParameter itemsInMethodParameter) {}

  @ShouldBeParsed
  public BasicEntities.ComplexType[] getComplexTypes() {
    return new BasicEntities.ComplexType[0];
  }

  BasicEntities.ReturnTypeOfNonPublicMethod ignored() {
    return new BasicEntities.ReturnTypeOfNonPublicMethod();
  }

  @ShouldBeParsed
  public BasicEntities.GenericItemInMethodReturnType<
          BasicEntities.GenericParameterInMethodReturnType>
      genericItemInMethodReturnType(
          BasicEntities.GenericParameterInMethodReturnType genericParameterInMethodReturnType) {
    return new BasicEntities.GenericItemInMethodReturnType<
        BasicEntities.GenericParameterInMethodReturnType>(genericParameterInMethodReturnType);
  }

  @ShouldBeParsed
  public BasicEntities.TypeThatWillBeConvertedByJackson getTypeThatWillBeConvertedByJackson() {
    return new BasicEntities.TypeThatWillBeConvertedByJackson();
  }

  @ShouldBeParsed
  @Override
  public BasicEntities.ReturnTypeInExposedMethod publicMethodOverriddenInEndpoint(
      BasicEntities.ParameterTypeInExposedMethod parameterTypeInExposedMethod) {
    return new BasicEntities.ReturnTypeInExposedMethod();
  }

  @ShouldBeParsed
  public BasicEntities.TypeUsingJsonIgnore getTypeUsingJsonIgnore() {
    return new BasicEntities.TypeUsingJsonIgnore(new BasicEntities.IgnoredType());
  }

  @ShouldBeParsed
  public @Nullable BasicEntities.NullableType everythingIsNullableHere(
      @Nullable BasicEntities.NullableType nullableParam1,
      @Nullable BasicEntities.NullableType nullableParam2) {
    return new BasicEntities.NullableType(
        new BasicEntities.NullableType(new BasicEntities.NullableType(null)));
  }

  @ShouldBeParsed
  @Override
  public int collectionSize(Collection<String> collection) {
    return collection.size();
  }

  @ShouldBeParsed
  public BasicEntities.SetterOnly setterOnly() {
    return new BasicEntities.SetterOnly();
  }

  @ShouldBeParsed
  public BasicEntities.AnnotationMess annotationMess() {
    return new BasicEntities.AnnotationMess();
  }

  @ShouldBeParsed
  public void threeParameters(int a, int b, int c) {}
}

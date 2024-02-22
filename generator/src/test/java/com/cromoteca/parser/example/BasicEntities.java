package com.cromoteca.parser.example;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.vaadin.hilla.Nonnull;
import com.vaadin.hilla.Nullable;
import java.util.List;
import java.util.Map;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.lang.NonNull;

public class BasicEntities {

  @ShouldBeParsed
  enum BasicEnum {
    A,
    B,
    C;
  }

  @ShouldBeParsed
  record ListItemInMethodReturnType(List<ListItemInRecordParameter> param) {}

  @ShouldBeParsed
  record ListItemInRecordParameter() {}

  @ShouldBeParsed
  record ArrayItemInMethodParameter(ArrayItemInRecordParameter[] param) {}

  @ShouldBeParsed
  static class ArrayItemInRecordParameter {
    private FieldTypeThatShouldNotBeParsed property;

    public PropertyTypeDifferentFromFieldType getProperty() {
      return property.field;
    }

    public void setProperty(PropertyTypeDifferentFromFieldType property) {
      this.property = new FieldTypeThatShouldNotBeParsed(property);
    }
  }

  static class FieldTypeThatShouldNotBeParsed {
    private PropertyTypeDifferentFromFieldType field;

    public FieldTypeThatShouldNotBeParsed(PropertyTypeDifferentFromFieldType field) {
      this.field = field;
    }
  }

  @ShouldBeParsed
  record PropertyTypeDifferentFromFieldType() {}

  @ShouldBeParsed
  record ComplexType(Map<Class<?>, List<ArrayTypeInListInMap>> map, BasicEnum enumItem) {}

  @ShouldBeParsed
  record ArrayTypeInListInMap() {}

  record ReturnTypeOfNonPublicMethod() {}

  @ShouldBeParsed
  record GenericItemInMethodReturnType<T>(T genericParam) {}

  @ShouldBeParsed
  record GenericParameterInMethodReturnType(
      PropertyInGenericParameter propertyInGenericParameter) {}

  @ShouldBeParsed
  record PropertyInGenericParameter(String s) {}

  record ReturnTypeInNotExposedMethod() {}

  record ParameterTypeInNotExposedMethod() {}

  @ShouldBeParsed
  record ReturnTypeInExposedMethod() {}

  @ShouldBeParsed
  record ParameterTypeInExposedMethod() {}

  @JsonSerialize(converter = ConverterUsedToSerialize.class)
  @JsonDeserialize(converter = ConverterUsedToDeserialize.class)
  record TypeThatWillBeConvertedByJackson() {}

  @ShouldBeParsed
  record TypeThatHasBeenConvertedByJackson() {}

  // Using a class since it looks like @JsonIgnore is not applicable to record components.
  @ShouldBeParsed
  static class TypeUsingJsonIgnore {
    private final IgnoredType param;

    public TypeUsingJsonIgnore(IgnoredType param) {
      this.param = param;
    }

    @JsonIgnore
    public IgnoredType param() {
      return param;
    }
  }

  record IgnoredType() {}

  public static class ConverterUsedToSerialize
      implements Converter<TypeThatWillBeConvertedByJackson, TypeThatHasBeenConvertedByJackson> {
    @Override
    public TypeThatHasBeenConvertedByJackson convert(TypeThatWillBeConvertedByJackson value) {
      return new TypeThatHasBeenConvertedByJackson();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
      return typeFactory.constructType(TypeThatWillBeConvertedByJackson.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
      return typeFactory.constructType(TypeThatHasBeenConvertedByJackson.class);
    }
  }

  public static class ConverterUsedToDeserialize
      implements Converter<TypeThatHasBeenConvertedByJackson, TypeThatWillBeConvertedByJackson> {
    @Override
    public TypeThatWillBeConvertedByJackson convert(TypeThatHasBeenConvertedByJackson value) {
      return new TypeThatWillBeConvertedByJackson();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
      return typeFactory.constructType(TypeThatHasBeenConvertedByJackson.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
      return typeFactory.constructType(TypeThatWillBeConvertedByJackson.class);
    }
  }

  // Also used to test a "recursive" type.
  @ShouldBeParsed
  public record NullableType(@Nullable NullableType nullableParameter) {}

  @ShouldBeParsed
  public static class SetterOnly {
    private String value = "";

    public void setValue(@Nonnull String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  @ShouldBeParsed
  public static class AnnotationMess {
    @NonNull private String value;

    @Nonnull
    public String getValue() {
      return value;
    }

    public void setValue(@Nullable String value) {
      this.value = value;
    }
  }
}

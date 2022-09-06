package dev.hilla.parser.example;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import java.util.List;
import java.util.Map;

public class BasicEntities {

    @ShouldBeParsed
    record ListItemInMethodReturnType(List<ListItemInRecordParameter> param) {
    }

    @ShouldBeParsed
    record ListItemInRecordParameter() {
    }

    @ShouldBeParsed
    record ArrayItemInMethodParameter(ArrayItemInRecordParameter[] param) {
    }

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
    record PropertyTypeDifferentFromFieldType() {
    }

    @ShouldBeParsed
    record ComplexType(Map<Class<?>, List<ArrayTypeInListInMap>> map) {
    }

    @ShouldBeParsed
    record ArrayTypeInListInMap() {
    }

    record ReturnTypeOfNonPublicMethod() {
    }

    @ShouldBeParsed
    record GenericItemInMethodReturnType<T>(T t) {
    }

    @ShouldBeParsed
    record GenericParameterInMethodReturnType(PropertyInGenericParameter propertyInGenericParameter) {
    }

    @ShouldBeParsed
    record PropertyInGenericParameter(String s) {
    }

    @JsonSerialize(converter = ConverterUsedToSerialize.class)
    @JsonDeserialize(converter = ConverterUsedToDeserialize.class)
    record TypeThatWillBeConvertedByJackson() {
    }

    @ShouldBeParsed
    record TypeThatHasBeenConvertedByJackson() {
    }

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
}

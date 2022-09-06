package dev.hilla.parser.example;

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
}

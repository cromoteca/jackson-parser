package dev.hilla.parser.example;

import java.util.Collection;

import dev.hilla.parser.annotations.EndpointExposed;

import static dev.hilla.parser.example.BasicEntities.*;

@EndpointExposed
public class ExposedSuperclass<T, U> {
    @ShouldBeParsed
    public ReturnTypeInExposedMethod publicMethodInExposedSuperclass
            (ParameterTypeInExposedMethod parameterTypeInExposedMethod) {
        return new ReturnTypeInExposedMethod();
    }

    public ReturnTypeInNotExposedMethod publicMethodOverriddenInNotExposedSuperclass
            (ParameterTypeInNotExposedMethod parameterTypeInNotExposedMethod) {
        return new ReturnTypeInNotExposedMethod();
    }

    @ShouldBeParsed
    public T getBackParameterizedType(T object) {
        return object;
    }

    public int collectionSize(Collection<U> collection) {
        return collection.size();
    }
}

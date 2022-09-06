package dev.hilla.parser.example;

import dev.hilla.parser.annotations.EndpointExposed;

import static dev.hilla.parser.example.BasicEntities.*;

@EndpointExposed
public class ExposedSuperclass {
    @ShouldBeParsed
    public ReturnTypeInExposedMethod publicMethodInExposedSuperclass
            (ParameterTypeInExposedMethod parameterTypeInExposedMethod) {
        return new ReturnTypeInExposedMethod();
    }

    public ReturnTypeInNotExposedMethod publicMethodOverriddenInNotExposedSuperclass
            (ParameterTypeInNotExposedMethod parameterTypeInNotExposedMethod) {
        return new ReturnTypeInNotExposedMethod();
    }
}

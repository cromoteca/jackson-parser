package dev.hilla.parser.example;

import static dev.hilla.parser.example.BasicEntities.*;

public class NotExposedSuperclass extends ExposedSuperclass {
    public ReturnTypeInNotExposedMethod publicMethodInNotExposedSuperclass
            (ParameterTypeInNotExposedMethod parameterTypeInNotExposedMethod) {
        return new ReturnTypeInNotExposedMethod();
    }

    @Override
    public ReturnTypeInNotExposedMethod publicMethodOverriddenInNotExposedSuperclass
            (ParameterTypeInNotExposedMethod parameterTypeInNotExposedMethod) {
        return new ReturnTypeInNotExposedMethod();
    }

    public ReturnTypeInExposedMethod publicMethodOverriddenInEndpoint
            (ParameterTypeInExposedMethod parameterTypeInExposedMethod) {
        return new ReturnTypeInExposedMethod();
    }
}

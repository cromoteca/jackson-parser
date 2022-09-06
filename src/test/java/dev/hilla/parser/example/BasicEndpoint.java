package dev.hilla.parser.example;

import dev.hilla.parser.annotations.Endpoint;

import java.util.List;

import static dev.hilla.parser.example.BasicEntities.*;

@Endpoint
public class BasicEndpoint extends NotExposedSuperclass {
    @ShouldBeParsed
    public List<ListItemInMethodReturnType> listItemInMethodReturnTypes() {
        return List.of(new ListItemInMethodReturnType(List.of(new ListItemInRecordParameter())));
    }

    @ShouldBeParsed
    public void setItemsInMethodParameter(ArrayItemInMethodParameter itemsInMethodParameter) {
    }

    @ShouldBeParsed
    public ComplexType[] getComplexTypes() {
        return new ComplexType[0];
    }

    ReturnTypeOfNonPublicMethod ignored() {
        return new ReturnTypeOfNonPublicMethod();
    }

    @ShouldBeParsed
    public GenericItemInMethodReturnType<GenericParameterInMethodReturnType> genericItemInMethodReturnType
            (GenericParameterInMethodReturnType genericParameterInMethodReturnType) {
        return new GenericItemInMethodReturnType<GenericParameterInMethodReturnType>(genericParameterInMethodReturnType);
    }

    @ShouldBeParsed
    public TypeThatWillBeConvertedByJackson getTypeThatWillBeConvertedByJackson() {
        return new TypeThatWillBeConvertedByJackson();
    }

    @ShouldBeParsed
    @Override
    public ReturnTypeInExposedMethod publicMethodOverriddenInEndpoint
            (ParameterTypeInExposedMethod parameterTypeInExposedMethod) {
        return new ReturnTypeInExposedMethod();
    }
}

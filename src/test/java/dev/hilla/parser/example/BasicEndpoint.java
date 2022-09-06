package dev.hilla.parser.example;

import dev.hilla.parser.annotations.Endpoint;

import java.util.List;

import static dev.hilla.parser.example.BasicEntities.*;

@Endpoint
public class BasicEndpoint {
    public List<ListItemInMethodReturnType> listItemInMethodReturnTypes() {
        return List.of(new ListItemInMethodReturnType(List.of(new ListItemInRecordParameter())));
    }

    public void setItemsInMethodParameter(ArrayItemInMethodParameter itemsInMethodParameter) {
    }

    public ComplexType[] getComplexTypes() {
        return new ComplexType[0];
    }

    ReturnTypeOfNonPublicMethod ignored() {
        return new ReturnTypeOfNonPublicMethod();
    }

    public GenericItemInMethodReturnType<GenericParameterInMethodReturnType> genericItemInMethodReturnType
            (GenericParameterInMethodReturnType genericParameterInMethodReturnType) {
        return new GenericItemInMethodReturnType<GenericParameterInMethodReturnType>(genericParameterInMethodReturnType);
    }

    public TypeThatWillBeConvertedByJackson getTypeThatWillBeConvertedByJackson() {
        return new TypeThatWillBeConvertedByJackson();
    }
}

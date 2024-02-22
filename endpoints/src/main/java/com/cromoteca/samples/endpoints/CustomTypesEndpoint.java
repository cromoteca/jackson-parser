package com.cromoteca.samples.endpoints;

import com.cromoteca.samples.endpoints.entities.MyOtherType;
import com.example.library.MyLibraryType;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import java.util.List;

@Endpoint
@AnonymousAllowed
public class CustomTypesEndpoint {
  public List<MyCustomType> fromCustomToListOfCustoms(MyCustomType argument) {
    return List.of(argument);
  }

  public MyOtherType fromArrayOfCustomsToCustom(MyOtherType[] argument) {
    return argument[0];
  }

  public MyLibraryType externalLibraryType(MyLibraryType argument) {
    return argument;
  }

  public static class MyCustomType {
    private String field;

    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }
  }
}

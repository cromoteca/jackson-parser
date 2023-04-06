package dev.hilla.generator.basic;

import com.example.library.MyLibraryType;
import dev.hilla.Endpoint;
import dev.hilla.generator.basic.entities.MyOtherType;
import java.util.List;

@Endpoint
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

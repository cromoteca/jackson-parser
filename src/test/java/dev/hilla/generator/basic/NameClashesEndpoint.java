package dev.hilla.generator.basic;

import dev.hilla.parser.annotations.Endpoint;
import java.util.List;

@Endpoint
public class NameClashesEndpoint {
  public List<MyOtherType> fromTypeToTypeWithSameName(
      dev.hilla.generator.basic.entities.MyOtherType argument) {
    return null;
  }

  public void clashWithEndpointRequestInitAndClientName(EndpointRequestInit client) {}

  public String doubleClient(String client1, String client2) {
    return client1 + client2;
  }

  public static class MyOtherType {
    private String field;

    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }
  }

  public static class EndpointRequestInit {
    private String field;

    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }
  }
}

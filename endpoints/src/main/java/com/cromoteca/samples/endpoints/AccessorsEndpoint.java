package com.cromoteca.samples.endpoints;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;

@Endpoint
@AnonymousAllowed
public class AccessorsEndpoint {
  public void accessors(Accessors arg) {}

  public static class Accessors {
    public int propertyWithField;

    private int propertyWithFieldAndGetter;

    public int getPropertyWithFieldAndGetter() {
      return propertyWithFieldAndGetter;
    }

    private int propertyWithFieldAndSetter;

    public void setPropertyWithFieldAndSetter(int propertyWithFieldAndSetter) {
      this.propertyWithFieldAndSetter = propertyWithFieldAndSetter;
    }

    private int propertyWithFieldAndGetterAndSetter;

    public int getPropertyWithFieldAndGetterAndSetter() {
      return propertyWithFieldAndGetterAndSetter;
    }

    public void setPropertyWithFieldAndGetterAndSetter(int propertyWithFieldAndGetterAndSetter) {
      this.propertyWithFieldAndGetterAndSetter = propertyWithFieldAndGetterAndSetter;
    }

    public int getPropertyWithGetter() {
      return 0;
    }

    public void setPropertyWithSetter(int arg) {}

    public int getPropertyWithGetterAndSetter() {
      return 0;
    }

    public void setPropertyWithGetterAndSetter(int arg) {}
  }
}

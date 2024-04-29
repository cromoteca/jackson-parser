package com.cromoteca.samples.exposed;

import com.vaadin.hilla.EndpointExposed;

@EndpointExposed
public class Exposed implements Something {
  public String getExposed() {
    return "Exposed";
  }

  @Override
  public String getSomething() {
    return "Something";
  }
}

package com.cromoteca.samples.endpoints;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;

@Endpoint
@AnonymousAllowed
public class EnumsEndpoint {
  public Color getRed() {
    return Color.RED;
  }

  public static enum Color {
    RED,
    GREEN,
    BLUE
  }
}

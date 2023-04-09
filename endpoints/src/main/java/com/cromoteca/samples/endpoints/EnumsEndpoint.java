package com.cromoteca.samples.endpoints;

import dev.hilla.Endpoint;

@Endpoint
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

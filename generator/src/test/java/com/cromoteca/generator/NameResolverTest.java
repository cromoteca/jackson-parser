package com.cromoteca.generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NameResolverTest {
  @Test
  public void testSplit() {
    assertArrayEquals(
        new String[] {"", "CustomTypesEndpoint"}, NameResolver.split("CustomTypesEndpoint"));
    assertArrayEquals(
        new String[] {"com.cromoteca.generator.basic", "CustomTypesEndpoint"},
        NameResolver.split("com.cromoteca.generator.basic.CustomTypesEndpoint"));
    assertArrayEquals(
        new String[] {"com.cromoteca.generator.basic.CustomTypesEndpoint", "MyCustomType"},
        NameResolver.split("com.cromoteca.generator.basic.CustomTypesEndpoint$MyCustomType"));
  }

  @Test
  public void testResolve() {
    assertEquals(
        "./CustomTypesEndpoint/MyCustomType",
        NameResolver.resolve(
            "com.cromoteca.generator.basic.CustomTypesEndpoint$MyCustomType",
            "com.cromoteca.generator.basic"));

    assertEquals(
        "../../../../connect-client.default.js",
        NameResolver.resolve("/connect-client.default.js", "com.cromoteca.generator.basic"));

    assertEquals(
        "@vaadin/hilla-frontend",
        NameResolver.resolve("@vaadin/hilla-frontend", "com.cromoteca.generator.basic"));
  }
}

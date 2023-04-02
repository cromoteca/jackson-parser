package dev.hilla.generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NameResolverTest {
  @Test
  public void testSplit() {
    assertArrayEquals(
        new String[] {"", "CustomTypesEndpoint"}, NameResolver.split("CustomTypesEndpoint"));
    assertArrayEquals(
        new String[] {"dev.hilla.generator.basic", "CustomTypesEndpoint"},
        NameResolver.split("dev.hilla.generator.basic.CustomTypesEndpoint"));
    assertArrayEquals(
        new String[] {"dev.hilla.generator.basic.CustomTypesEndpoint", "MyCustomType"},
        NameResolver.split("dev.hilla.generator.basic.CustomTypesEndpoint$MyCustomType"));
  }

  @Test
  public void testResolve() {
    assertEquals(
        "./CustomTypesEndpoint/MyCustomType",
        NameResolver.resolve(
            "dev.hilla.generator.basic.CustomTypesEndpoint$MyCustomType",
            "dev.hilla.generator.basic"));

    assertEquals(
        "../../../../connect-client.default.js",
        NameResolver.resolve("/connect-client.default.js", "dev.hilla.generator.basic"));

    assertEquals(
        "@hilla/frontend", NameResolver.resolve("@hilla/frontend", "dev.hilla.generator.basic"));
  }
}

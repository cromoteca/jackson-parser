package dev.hilla.generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NameResolverTest {
  @Test
  public void testSplit() {
    assertArrayEquals(
        new String[] {"", "CustomTypesEndpoint"}, NameResolver.split("CustomTypesEndpoint"));
    assertArrayEquals(
        new String[] {"dev.hilla.generator.example", "CustomTypesEndpoint"},
        NameResolver.split("dev.hilla.generator.example.CustomTypesEndpoint"));
    assertArrayEquals(
        new String[] {"dev.hilla.generator.example.CustomTypesEndpoint", "MyCustomType"},
        NameResolver.split("dev.hilla.generator.example.CustomTypesEndpoint$MyCustomType"));
  }

  @Test
  public void testResolve() {
    assertEquals(
        "./CustomTypesEndpoint/MyCustomType",
        NameResolver.resolve(
            "dev.hilla.generator.example.CustomTypesEndpoint$MyCustomType",
            "dev.hilla.generator.example"));

    assertEquals(
        "../../../../connect-client.default.js",
        NameResolver.resolve("/connect-client.default.js", "dev.hilla.generator.example"));

    assertEquals(
        "@hilla/frontend", NameResolver.resolve("@hilla/frontend", "dev.hilla.generator.example"));
  }
}

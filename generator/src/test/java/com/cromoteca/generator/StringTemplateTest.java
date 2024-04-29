package com.cromoteca.generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StringTemplateTest {
  private static class BasicTemplateBean {
    private final String hello;

    private BasicTemplateBean(String hello) {
      this.hello = hello;
    }

    public String getHello() {
      return hello;
    }

    public Object goodbye() {
      return new StringBuilder().append("Goodbye");
    }
  }

  @Test
  public void testBasicTemplate() {
    var bean = new BasicTemplateBean("World");
    assertEquals("Hello, World!", StringTemplate.from("Hello, ${hello}!", bean), "Basic");
    assertEquals(
        "Hello, World!",
        StringTemplate.from("Hello, ${ hello }!", bean),
        "With spaces inside curly braces");
    assertEquals(
        "Hello, ${hello}!", StringTemplate.from("Hello, $${hello}!", bean), "Double $$ means $");
    assertEquals(
        "Hello, $World!",
        StringTemplate.from("Hello, $$${hello}!", bean),
        "$ followed by replacement");
    assertEquals(
        "Hello, $ {hello}!",
        StringTemplate.from("Hello, $ {hello}!", bean),
        "Space between $ and {");
    assertEquals(
        "Hello, World!",
        StringTemplate.from("Hello, ${hello}${unknown}!", bean),
        "Unknown property");
    assertEquals(
        """
        Hello,
        World!
        """,
        StringTemplate.from(
            """
            Hello,
            ${hello}!
            """,
            bean),
        "String block");
    assertEquals("Goodbye!", StringTemplate.from("${goodbye}!", bean));
  }
}

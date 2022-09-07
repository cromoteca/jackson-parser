package dev.hilla.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTemplateTest {
    public static class BasicTemplateBean {
        private final String hello;

        BasicTemplateBean(String hello) {
            this.hello = hello;
        }

        public String getHello() {
            return hello;
        }
    }

    @Test
    public void testBasicTemplate() {
        var bean = new BasicTemplateBean("World");
        assertEquals("Hello, World!", StringTemplate.generate(bean, "Hello, ${hello}!"));
        assertEquals("Hello, World!", StringTemplate.generate(bean, "Hello, ${ hello }!"));
        assertEquals("Hello, ${hello}!", StringTemplate.generate(bean, "Hello, $${hello}!"));
        assertEquals("Hello, $${hello}!", StringTemplate.generate(bean, "Hello, $$${hello}!"));
        assertEquals("Hello, $ {hello}!", StringTemplate.generate(bean, "Hello, $ {hello}!"));
        assertEquals("Hello, World!", StringTemplate.generate(bean, "Hello, ${hello}${unknown}!"));
        assertEquals("""
                Hello,
                World!
                """, StringTemplate.generate(bean, """
                Hello,
                ${hello}!
                """));
    }
}
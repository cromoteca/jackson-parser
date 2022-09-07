package dev.hilla.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringTemplateTest {
    @Test
    public void testBasicTemplate() {
        var bean = new BasicTemplateBean("World");
        assertEquals("Hello, World!", StringTemplate.generate(bean, "Hello, ${text}!"));
        assertEquals("Hello, World!", StringTemplate.generate(bean, "Hello, ${ text }!"));
        assertEquals("Hello, ${text}!", StringTemplate.generate(bean, "Hello, $${text}!"));
        assertEquals("Hello, $${text}!", StringTemplate.generate(bean, "Hello, $$${text}!"));
        assertEquals("Hello, $ {text}!", StringTemplate.generate(bean, "Hello, $ {text}!"));
        assertEquals("Hello, World!", StringTemplate.generate(bean, "Hello, ${text}${unknown}!"));
        assertEquals("""
                Hello,
                World!
                """, StringTemplate.generate(bean, """
                Hello,
                ${text}!
                """));
    }

    @Test
    @Disabled
    public void testCollectionsInTemplate() {
        var bean = new BasicTemplateBean("Prime numbers", 1, 2, 3, 5, 7);
        var mainTemplate = "${text}: ${numbers#list}";
        assertEquals("Prime numbers: 1,2,3,5,7...", StringTemplate.generate(bean, mainTemplate));
    }

    private void boh() {
        var template = new StringTemplate("Hello, ${text}!").add("list", "${item}");
    }

    public static class BasicTemplateBean {
        private final String text;
        private final List<Number> numbers;

        BasicTemplateBean(String text) {
            this.text = text;
            numbers = List.of();
        }

        public BasicTemplateBean(String text, List<Number> numbers) {
            this.text = text;
            this.numbers = numbers;
        }

        public BasicTemplateBean(String text, Number... numbers) {
            this.text = text;
            this.numbers = Arrays.asList(numbers);
        }

        public String getText() {
            return text;
        }

        public List<Number> getNumbers() {
            return numbers;
        }
    }
}
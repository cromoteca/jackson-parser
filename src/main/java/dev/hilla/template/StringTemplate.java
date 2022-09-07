package dev.hilla.template;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTemplate {
    private static final Pattern replacement = Pattern.compile("(\\$+)\\{\\s*(\\w+)(#\\w+)?\\s*}");
    private final String mainTemplate;
    private final Map<String, String> templates = new HashMap<>();

    public StringTemplate() {
        mainTemplate = null;
    }

    public StringTemplate(String mainTemplate) {
        this.mainTemplate = mainTemplate;
    }

    public static String generate(Object bean, String template) {
        var matcher = replacement.matcher(template);
        var output = new StringBuilder();

        while (matcher.find()) {
            var dollars = matcher.group(1);
            var propertyName = matcher.group(2);

            if (dollars.length() == 1) {
                try {
                    if (PropertyUtils.isReadable(bean, propertyName)) {
                        matcher.appendReplacement(output, Objects.toString(PropertyUtils.getProperty(bean, propertyName), ""));
                    } else {
                        matcher.appendReplacement(output, "");
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            } else {
                matcher.appendReplacement(output, Matcher.quoteReplacement(dollars.substring(0, dollars.length() - 1)));
                output.append('{').append(propertyName).append('}');
            }
        }
        matcher.appendTail(output);

        return output.toString();
    }

    public StringTemplate add(String name, String template) {
        templates.put(name, template);
        return this;
    }

    public String apply(String text) {
        return doApply(mainTemplate, text);
    }

    public String apply(String templateName, String text) {
        return doApply(templates.get(templateName), text);
    }

    private String doApply(String template, String text) {
        return null;
    }
}

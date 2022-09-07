package dev.hilla.template;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTemplate {
    private static final Pattern replacement = Pattern.compile("(\\$+)\\{\\s*(\\w+)\\s*}");

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
}

package dev.hilla.template;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringTemplate {
    private static final Pattern replacement = Pattern.compile("(\\$+)\\{\\s*(\\w+)\\s*}");

    public static String generate(Object bean, String template) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(bean.getClass());
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
        var values = Arrays.stream(beanInfo.getPropertyDescriptors())
                .collect(Collectors.toMap(FeatureDescriptor::getName, propertyDescriptor -> {
                    try {
                        return propertyDescriptor.getReadMethod().invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }));
        var matcher = replacement.matcher(template);
        var output = new StringBuilder();

        while (matcher.find()) {
            var dollars = matcher.group(1);
            var propertyName = matcher.group(2);

            if (dollars.length() == 1) {
                matcher.appendReplacement(output, Objects.toString(values.get(propertyName), ""));
            } else {
                matcher.appendReplacement(output, Matcher.quoteReplacement(dollars.substring(0, dollars.length() - 1)));
                output.append('{').append(propertyName).append('}');
            }
        }
        matcher.appendTail(output);

        return output.toString();
    }
}

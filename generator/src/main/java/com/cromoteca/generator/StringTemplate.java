/*
 * Copyright (C) 2023 Luciano Vernaschi (luciano at cromoteca.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cromoteca.generator;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.lang.NonNull;

public class StringTemplate {
  private static final Pattern EXPRESSION = Pattern.compile("(\\$+)(\\{\\s*(\\w+)\\s*})");

  public static @NonNull String from(String template, Object bean) {
    BeanInfo beanInfo;
    try {
      beanInfo = Introspector.getBeanInfo(bean.getClass());
    } catch (IntrospectionException e) {
      return "";
    }
    var values =
        Arrays.stream(beanInfo.getPropertyDescriptors())
            .filter(p -> p.getReadMethod() != null)
            .collect(
                Collectors.toMap(PropertyDescriptor::getName, PropertyDescriptor::getReadMethod));
    return from(
        template,
        key ->
            Optional.ofNullable(values.get(key))
                // If a property is found, use its getter.
                .map(
                    method -> {
                      try {
                        return method.invoke(bean);
                      } catch (IllegalAccessException | InvocationTargetException e) {
                        return "";
                      }
                    })
                .orElseGet(
                    // As a fallback, try to use the property name as a method name with no
                    // parameters.
                    () -> {
                      try {
                        return bean.getClass().getMethod(key).invoke(bean);
                      } catch (Exception ex) {
                        return "";
                      }
                    }));
  }

  public static @NonNull String from(String template, Map<?, ?> map) {
    return from(template, map::get);
  }

  public static @NonNull String from(String template, Function<String, Object> values) {
    var matcher = EXPRESSION.matcher(template);
    var output = new StringBuilder();

    while (matcher.find()) {
      var dollars = matcher.group(1);
      var expression = matcher.group(2);
      var propertyName = matcher.group(3);

      var escapedDollars = dollars.substring(0, dollars.length() / 2);
      var replacement =
          dollars.length() % 2 == 1 ? Objects.toString(values.apply(propertyName), "") : expression;

      matcher.appendReplacement(output, Matcher.quoteReplacement(escapedDollars + replacement));
    }

    matcher.appendTail(output);
    return output.toString();
  }
}

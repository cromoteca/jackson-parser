package dev.hilla.parser.model;

import java.lang.reflect.Method;
import java.util.List;

public record MethodClass(Class<?> type, List<Method> methods) {
}

package dev.hilla.parser.model;

import java.lang.reflect.AccessibleObject;
import java.util.List;

public record EntityClass(String name, List<AccessibleObject> propertyAccessors) {
}

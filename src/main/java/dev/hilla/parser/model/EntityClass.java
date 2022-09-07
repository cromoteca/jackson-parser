package dev.hilla.parser.model;

import java.lang.reflect.Member;
import java.util.List;

public record EntityClass(String name, List<? extends Member> propertyAccessors) {
}

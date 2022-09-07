package dev.hilla.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleConsoleOutput {
    public static void describe(Parser.ParserResult parserResult) {
        parserResult.endpoints().stream().forEach(e -> {
            System.out.println(e.type().getName());
            e.methods().forEach(SimpleConsoleOutput::describe);
            System.out.println();
        });
        parserResult.entities().stream().forEach(e -> {
            System.out.println(e.name());
            e.propertyAccessors().forEach(member -> {
                if (member instanceof Method m) {
                    describe(m);
                } else if (member instanceof Field f) {
                    describe(f);
                } else {
                    System.out.println("Unknown member " + member.getName() + " " + member.getClass().getSimpleName());
                }
            });
            System.out.println();
        });
    }

    private static void describe(Method m) {
        System.out.format("%s %s(%s): %s\n", annotations(m), m.getName(), parameters(m), m.getReturnType().getSimpleName());
    }

    private static void describe(Field f) {
        System.out.format("%s %s: %s\n", annotations(f), f.getName(), f.getType().getName());
    }

    private static String annotations(Member p) {
        var other = annotations(p.getDeclaringClass());

        if (p instanceof AccessibleObject ao) {
            other = Stream.concat(Arrays.stream(ao.getDeclaredAnnotations()), other);
        }

        return other.distinct().map(a -> '@' + a.annotationType().getSimpleName()).sorted().collect(Collectors.joining(" "));
    }

    private static String parameters(Method m) {
        return Arrays.stream(m.getParameterTypes()).map(p -> String.format("%s: %s", p.getSimpleName(), parameter(p))).collect(Collectors.joining(", "));
    }

    private static String parameter(Class<?> c) {
        var ann = annotations(c).distinct().map(a -> '@' + a.annotationType().getSimpleName()).sorted().collect(Collectors.joining(" "));
        return String.format("%s %s", ann, c.getSimpleName());
    }

    private static Stream<Annotation> annotations(Class<?> c) {
        return Stream.concat(Arrays.stream(c.getAnnotations()),
                Arrays.stream(c.getPackage().getAnnotations()));
    }
}

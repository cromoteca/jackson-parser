package dev.hilla.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleConsoleOutput {
  public static void describe(ScanResult parserResult) {
    System.out.println("Endpoints: " + parserResult.endpoints().size());
    System.out.println("Entities: " + parserResult.entities().size());

    parserResult
        .endpoints()
        .forEach(
            e -> {
              System.out.println(e.type().getName());
              e.methods()
                  .forEach(
                      m ->
                          System.out.format(
                              "%s(%s): %s\n",
                              describe(m), parameters(m), describe(m.getAnnotatedReturnType())));
              System.out.println();
            });
    parserResult
        .entities()
        .forEach(
            e -> {
              System.out.println(e.name());
              e.propertyAccessors()
                  .forEach(
                      member -> {
                        if (member instanceof Method m) {
                          System.out.format(
                              "%s(%s): %s\n",
                              describe(m), parameters(m), describe(m.getAnnotatedReturnType()));
                        } else if (member instanceof Field f) {
                          System.out.format("%s: %s\n", describe(f), f.getType().getName());
                        } else {
                          System.out.println(
                              "Unknown member "
                                  + member.getName()
                                  + " "
                                  + member.getClass().getSimpleName());
                        }
                      });
              System.out.println();
            });
  }

  private static String describe(Member p) {
    var other = annotations(p.getDeclaringClass());

    if (p instanceof AccessibleObject ao) {
      other = Stream.concat(Arrays.stream(ao.getDeclaredAnnotations()), other);
    }

    var ann =
        other
            .distinct()
            .map(a -> '@' + a.annotationType().getSimpleName())
            .sorted()
            .collect(Collectors.joining(" "));
    return String.join(" ", ann, p.getName()).trim();
  }

  private static String parameters(Method m) {
    return Arrays.stream(m.getAnnotatedParameterTypes())
        .map(p -> String.format("%s: %s", p.getType(), describe(p)))
        .collect(Collectors.joining(", "));
  }

  private static String describe(AnnotatedType at) {
    var ann =
        Arrays.stream(at.getAnnotations())
            .distinct()
            .map(a -> '@' + a.annotationType().getSimpleName())
            .sorted()
            .collect(Collectors.joining(" "));
    return String.join(" ", ann, describe(at.getType())).trim();
  }

  private static String describe(Type t) {
    if (t instanceof Class<?> c) {
      return describeClass(c);
    } else if (t instanceof ParameterizedType pt) {
      var x = describeClass((Class<?>) pt.getRawType());
      var y =
          Arrays.stream(pt.getActualTypeArguments())
              .map(SimpleConsoleOutput::describe)
              .collect(Collectors.joining(", "));
      return x + "<" + y + ">";
    } else if (t instanceof TypeVariable<?> tv) {
      return tv.getName();
    } else if (t instanceof WildcardType wt) {
      return wt.getTypeName();
    }

    throw new UnsupportedOperationException(t.getClass() + " is unknown");
  }

  private static String describeClass(Class<?> c) {
    var ann =
        annotations(c)
            .distinct()
            .map(a -> '@' + a.annotationType().getSimpleName())
            .sorted()
            .collect(Collectors.joining(" "));
    return String.join(" ", ann, c.getSimpleName()).trim();
  }

  private static Stream<Annotation> annotations(Class<?> c) {
    Stream<Annotation> stream = Arrays.stream(c.getAnnotations());
    Package pack;
    if ((pack = c.getPackage()) != null) {
      stream = Stream.concat(stream, Arrays.stream(pack.getAnnotations()));
    }
    return stream;
  }
}

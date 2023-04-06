package com.cromoteca.generator;

import java.nio.file.Path;
import java.util.Arrays;

public class NameResolver {

  public static String[] split(String name) {
    String[] parts = name.split("\\.|\\$");
    if (parts.length == 1) {
      return new String[] {"", parts[0]};
    } else {
      String className = parts[parts.length - 1];
      String packageName = String.join(".", Arrays.copyOfRange(parts, 0, parts.length - 1));
      return new String[] {packageName, className};
    }
  }

  public static String resolve(String name, String packageName) {
    if (name.startsWith("@")) {
      return name;
    }

    var namePath =
        Path.of(name.contains("/") ? name : '/' + String.join("/", name.split("\\.|\\$")));
    var packagePath = Path.of('/' + String.join("/", packageName.split("\\.")));
    var relativePath = packagePath.relativize(namePath).toString().replace('\\', '/');

    return relativePath.startsWith(".") ? relativePath : "./" + relativePath;
  }
}

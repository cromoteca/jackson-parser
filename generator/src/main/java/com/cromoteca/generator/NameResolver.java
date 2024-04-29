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

import java.nio.file.Path;
import java.util.Arrays;

public class NameResolver {

  public static String[] split(String name) {
    String[] parts = name.split("[.$]");
    if (parts.length == 1) {
      return new String[] {"", parts[0]};
    } else {
      String className = parts[parts.length - 1];
      String packageName = String.join(".", Arrays.copyOfRange(parts, 0, parts.length - 1));
      return new String[] {packageName, className};
    }
  }

  public static boolean isLibrary(String name) {
    return name.startsWith("@")
        || (Character.isLowerCase(name.charAt(0)) && name.indexOf('.') == -1);
  }

  public static String resolve(String name, String packageName) {
    if (isLibrary(name)) {
      return name;
    }

    var namePath = Path.of(name.contains("/") ? name : '/' + String.join("/", name.split("[.$]")));
    var packagePath = Path.of('/' + String.join("/", packageName.split("\\.")));
    var relativePath = packagePath.relativize(namePath).toString().replace('\\', '/');

    return relativePath.startsWith(".") ? relativePath : "./" + relativePath;
  }
}

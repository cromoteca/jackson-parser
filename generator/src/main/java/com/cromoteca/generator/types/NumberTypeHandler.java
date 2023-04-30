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
package com.cromoteca.generator.types;

import java.util.Set;

public class NumberTypeHandler extends DefaultTypeHandler {

  public static final Set<Class<?>> SUPPORTED =
      Set.of(
          Byte.class,
          Short.class,
          Integer.class,
          Long.class,
          Float.class,
          Double.class,
          byte.class,
          short.class,
          int.class,
          long.class,
          float.class,
          double.class);

  @Override
  public boolean isSupported(Class<?> cls) {
    return SUPPORTED.contains(cls);
  }

  @Override
  public String parameterType(Class<?> type) {
    return "number";
  }

  @Override
  public String modelType(Class<?> type) {
    return "NumberModel";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }

  @Override
  public String emptyValue() {
    return "0";
  }
}

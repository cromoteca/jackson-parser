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

public class UnknownTypeHandler extends DefaultTypeHandler {
  @Override
  public boolean isSupported(Class<?> cls) {
    return cls.getName().startsWith("java.");
  }

  @Override
  public String parameterType(Class<?> type) {
    return "unknown";
  }

  @Override
  public String modelType(Class<?> type) {
    return "ObjectModel";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }
}

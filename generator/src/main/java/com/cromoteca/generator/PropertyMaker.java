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

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.SortedSet;

class PropertyMaker {
  private final Generator.MakerTools tools;
  private final BeanPropertyDefinition property;
  private final SortedSet<String> typeVariables;

  PropertyMaker(
      Generator.MakerTools tools,
      BeanPropertyDefinition property,
      SortedSet<String> typeVariables) {
    this.tools = tools;
    this.property = property;
    this.typeVariables = typeVariables;
  }

  String generate() {
    var propertyType = MultipleType.forProperty(property);
    String type = tools.generateType(propertyType, typeVariables);

    return
        """
      \s   %s%s: %s;"""
        .formatted(property.getName(), type.endsWith(Generator.NULLABLE_SUFFIX) ? "?" : "", type);
  }
}

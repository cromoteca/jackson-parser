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

class EntityModelFunctionMaker {
  private final Generator.MakerTools tools;
  private final BeanPropertyDefinition property;
  private final MultipleType propertyType;
  private final String _getPropertyModel;

  EntityModelFunctionMaker(Generator.MakerTools tools, BeanPropertyDefinition property) {
    this.tools = tools;
    this.property = property;
    propertyType = MultipleType.forProperty(property);
    _getPropertyModel = tools.fromImport("_getPropertyModel", "@hilla/form", false, false);
  }

  String generate() {
    return StringTemplate.from(
        """

        \s   get ${name}(): ${modelType} {
                return this[${getPropertyModel}]('${name}', ${modelType}, [${nullable}]) as ${modelType};
            }""",
        this);
  }

  private String name() {
    return property.getName();
  }

  private String getPropertyModel() {
    return _getPropertyModel;
  }

  private String modelType() {
    return tools.fromImport("StringModel", "@hilla/form", false, false);
  }

  private String nullable() {
    return "false";
  }
}

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

class Import {
  private final String variable;
  private final String from;
  private final boolean asDefault;
  private boolean type;
  private final String alias;

  Import(String variable, String from, boolean asDefault, boolean type, String alias) {
    this.variable = variable;
    this.from = from;
    this.asDefault = asDefault;
    this.type = type;
    this.alias = alias;
  }

  public String getVariable() {
    return variable;
  }

  public String getFrom() {
    return from;
  }

  public boolean isDefault() {
    return asDefault;
  }

  public boolean isType() {
    return type;
  }

  public void setType(boolean type) {
    this.type = type;
  }

  public String getAlias() {
    return alias;
  }

  public String toString() {
    var sb = new StringBuilder();

    if (type) {
      sb.append("type ");
    }

    if (variable.equals(alias)) {
      sb.append(variable);
    } else if (isDefault()) {
      sb.append(alias);
    } else {
      sb.append(variable).append(" as ").append(alias);
    }

    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Import anImport = (Import) o;

    if (!variable.equals(anImport.variable)) {
      return false;
    }

    return from.equals(anImport.from);
  }

  @Override
  public int hashCode() {
    int result = variable.hashCode();
    result = 31 * result + from.hashCode();
    return result;
  }
}

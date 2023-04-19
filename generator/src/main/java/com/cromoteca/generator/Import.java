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

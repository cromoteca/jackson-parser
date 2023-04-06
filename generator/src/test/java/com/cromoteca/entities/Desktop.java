package com.cromoteca.entities;

public class Desktop<T> {
  private final Chair<Golf, Snowboard> freedom;
  private Bridge reality;

  public Desktop(Chair<Golf, Snowboard> freedom) {
    this.freedom = freedom;
  }

  public Bridge getReality() {
    return reality;
  }

  public void setReality(Bridge reality) {
    this.reality = reality;
  }

  public Chair<Golf, Snowboard> getFreedom() {
    return freedom;
  }
}

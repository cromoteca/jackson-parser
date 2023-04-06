package com.cromoteca.entities;

public class Everybody {
  private final Love<Hunger<Pancake>> adulthood;

  public Everybody(Love<Hunger<Pancake>> adulthood) {
    this.adulthood = adulthood;
  }

  public Love<Hunger<Pancake>> getAdulthood() {
    return adulthood;
  }
}

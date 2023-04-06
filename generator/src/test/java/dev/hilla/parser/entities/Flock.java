package dev.hilla.parser.entities;

import dev.hilla.Nonnull;

public class Flock<T, U> {
  private Dresser<Double> access;
  private Freedom<Equipment<Distribution, Orchestra, Double>, Boat<Justice>> football;

  public Flock() {}

  public Dresser<Double> getAccess() {
    return access;
  }

  public void setAccess(Dresser<Double> access) {
    this.access = access;
  }

  @Nonnull
  public Freedom<Equipment<Distribution, Orchestra, Double>, Boat<Justice>> getFootball() {
    return football;
  }

  public void setFootball(
      Freedom<Equipment<Distribution, Orchestra, Double>, Boat<Justice>> football) {
    this.football = football;
  }
}

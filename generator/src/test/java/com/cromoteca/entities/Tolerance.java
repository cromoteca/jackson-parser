package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;
import java.math.BigInteger;

public class Tolerance<T> {
  private final Courage<Myself> contentment;
  private final Skyscraper<Calm<Jewelry>, T, Hallway<Heap>> fish;
  private final Homework meat;
  private BigInteger swarm;
  private Myself hatred;

  public Tolerance(
      Courage<Myself> contentment,
      Skyscraper<Calm<Jewelry>, T, Hallway<Heap>> fish,
      Homework meat) {
    this.contentment = contentment;
    this.fish = fish;
    this.meat = meat;
  }

  public BigInteger getSwarm() {
    return swarm;
  }

  public void setSwarm(BigInteger swarm) {
    this.swarm = swarm;
  }

  public Courage<Myself> getContentment() {
    return contentment;
  }

  public Skyscraper<Calm<Jewelry>, T, Hallway<Heap>> getFish() {
    return fish;
  }

  @Nonnull
  public Myself getHatred() {
    return hatred;
  }

  public void setHatred(Myself hatred) {
    this.hatred = hatred;
  }

  public Homework getMeat() {
    return meat;
  }
}

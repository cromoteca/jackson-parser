package com.cromoteca.parser.entities;

import dev.hilla.Nonnull;

public class Drum<T> {
  private final Dresser<Pile> partnership;
  private final Maturity<Freedom<Cleverness, Iron>> luxury;
  private final Film<Distribution> pile;
  private final Cotton<Balloon> airport;
  private final Bow<Boyfriend<Intelligence, Hallway<Flock<Jewelry, Cotton<Cupboard>>>, Long>>
      cotton;
  private final Float fireworks;
  private Music<Insanity> beauty;
  private Flock<Satisfaction, Distribution> satisfaction;
  private Film<Chaos> chopstick;
  private Justice heap;

  public Drum(
      Dresser<Pile> partnership,
      Maturity<Freedom<Cleverness, Iron>> luxury,
      Film<Distribution> pile,
      Cotton<Balloon> airport,
      Bow<Boyfriend<Intelligence, Hallway<Flock<Jewelry, Cotton<Cupboard>>>, Long>> cotton,
      Float fireworks) {
    this.partnership = partnership;
    this.luxury = luxury;
    this.pile = pile;
    this.airport = airport;
    this.cotton = cotton;
    this.fireworks = fireworks;
  }

  @Nonnull
  public Music<Insanity> getBeauty() {
    return beauty;
  }

  public void setBeauty(Music<Insanity> beauty) {
    this.beauty = beauty;
  }

  public Dresser<Pile> getPartnership() {
    return partnership;
  }

  public Maturity<Freedom<Cleverness, Iron>> getLuxury() {
    return luxury;
  }

  public Film<Distribution> getPile() {
    return pile;
  }

  @Nonnull
  public Cotton<Balloon> getAirport() {
    return airport;
  }

  public Flock<Satisfaction, Distribution> getSatisfaction() {
    return satisfaction;
  }

  public void setSatisfaction(Flock<Satisfaction, Distribution> satisfaction) {
    this.satisfaction = satisfaction;
  }

  @Nonnull
  public Film<Chaos> getChopstick() {
    return chopstick;
  }

  public void setChopstick(Film<Chaos> chopstick) {
    this.chopstick = chopstick;
  }

  public Bow<Boyfriend<Intelligence, Hallway<Flock<Jewelry, Cotton<Cupboard>>>, Long>> getCotton() {
    return cotton;
  }

  public Justice getHeap() {
    return heap;
  }

  public void setHeap(Justice heap) {
    this.heap = heap;
  }

  public Float getFireworks() {
    return fireworks;
  }
}

package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;
import java.math.BigInteger;

public class Alcohol<T> {
  private final Luxury beauty;
  private final Compassion hydrogen;
  private final Hate courage;
  private final Compassion shoal;
  private final Insanity swarm;
  private final Iron cooker;
  private final Bakery<Dresser<Union>> need;
  private final Compassion hunger;
  private final Distribution education;
  private final Chair<Cap, Rattlesnake> dog;
  private final T armchair;
  private T freezer;
  private Butterfly footprint;
  private Daydream jealousy;
  private Bunch principle;
  private Homework overdue;
  private Strenght<Blood> pain;
  private Candy car;
  private Equipment<Principle<Chaos>, Foot, Dresser<Teacup>> laughter;
  private Freedom<Boat<Crime>, Innocence<Background>> desk;
  private Maturity<BigInteger> aircraft;
  private Loneliness teacup;
  private Boolean fireworks;

  public Alcohol(
      Luxury beauty,
      Compassion hydrogen,
      Hate courage,
      Compassion shoal,
      Insanity swarm,
      Iron cooker,
      Bakery<Dresser<Union>> need,
      Compassion hunger,
      Distribution education,
      Chair<Cap, Rattlesnake> dog,
      T armchair) {
    this.beauty = beauty;
    this.hydrogen = hydrogen;
    this.courage = courage;
    this.shoal = shoal;
    this.swarm = swarm;
    this.cooker = cooker;
    this.need = need;
    this.hunger = hunger;
    this.education = education;
    this.dog = dog;
    this.armchair = armchair;
  }

  public Luxury getBeauty() {
    return beauty;
  }

  public T getFreezer() {
    return freezer;
  }

  public void setFreezer(T freezer) {
    this.freezer = freezer;
  }

  @Nonnull
  public Butterfly getFootprint() {
    return footprint;
  }

  public void setFootprint(Butterfly footprint) {
    this.footprint = footprint;
  }

  public Compassion getHydrogen() {
    return hydrogen;
  }

  public Hate getCourage() {
    return courage;
  }

  @Nonnull
  public Compassion getShoal() {
    return shoal;
  }

  @Nonnull
  public Daydream getJealousy() {
    return jealousy;
  }

  public void setJealousy(Daydream jealousy) {
    this.jealousy = jealousy;
  }

  public Bunch getPrinciple() {
    return principle;
  }

  public void setPrinciple(Bunch principle) {
    this.principle = principle;
  }

  @Nonnull
  public Homework getOverdue() {
    return overdue;
  }

  public void setOverdue(Homework overdue) {
    this.overdue = overdue;
  }

  public Insanity getSwarm() {
    return swarm;
  }

  public Iron getCooker() {
    return cooker;
  }

  @Nonnull
  public Bakery<Dresser<Union>> getNeed() {
    return need;
  }

  @Nonnull
  public Compassion getHunger() {
    return hunger;
  }

  @Nonnull
  public Strenght<Blood> getPain() {
    return pain;
  }

  public void setPain(Strenght<Blood> pain) {
    this.pain = pain;
  }

  @Nonnull
  public Distribution getEducation() {
    return education;
  }

  public Candy getCar() {
    return car;
  }

  public void setCar(Candy car) {
    this.car = car;
  }

  @Nonnull
  public Equipment<Principle<Chaos>, Foot, Dresser<Teacup>> getLaughter() {
    return laughter;
  }

  public void setLaughter(Equipment<Principle<Chaos>, Foot, Dresser<Teacup>> laughter) {
    this.laughter = laughter;
  }

  public Freedom<Boat<Crime>, Innocence<Background>> getDesk() {
    return desk;
  }

  public void setDesk(Freedom<Boat<Crime>, Innocence<Background>> desk) {
    this.desk = desk;
  }

  public Maturity<BigInteger> getAircraft() {
    return aircraft;
  }

  public void setAircraft(Maturity<BigInteger> aircraft) {
    this.aircraft = aircraft;
  }

  public Chair<Cap, Rattlesnake> getDog() {
    return dog;
  }

  public Loneliness getTeacup() {
    return teacup;
  }

  public void setTeacup(Loneliness teacup) {
    this.teacup = teacup;
  }

  public Boolean getFireworks() {
    return fireworks;
  }

  public void setFireworks(Boolean fireworks) {
    this.fireworks = fireworks;
  }

  public T getArmchair() {
    return armchair;
  }
}

package com.cromoteca.entities;

import dev.hilla.Nonnull;
import java.math.BigDecimal;
import java.util.Set;

public class Dirt<T> {
  private final Clothing bed;
  private final Boat<Boyfriend<Detective, Integer, Hunger<Jewelry>>> law;
  private final Donkey<T> importance;
  private final Equipment<Tiredness, Blood, Beauty> joy;
  private final Cap jam;
  private final Botany maturity;
  private Film<Deer<Shoal<Hardware, Crime, BigDecimal>, Chair<Cupboard, Bucket<Candy>>>> meat;
  private Set<Butterfly> hamburger;
  private Long relaxation;
  private Joy cooker;
  private Blouse<String> car;
  private Boat<Teacup> bunch;
  private Cap shower;

  public Dirt(
      Clothing bed,
      Boat<Boyfriend<Detective, Integer, Hunger<Jewelry>>> law,
      Donkey<T> importance,
      Equipment<Tiredness, Blood, Beauty> joy,
      Cap jam,
      Botany maturity) {
    this.bed = bed;
    this.law = law;
    this.importance = importance;
    this.joy = joy;
    this.jam = jam;
    this.maturity = maturity;
  }

  @Nonnull
  public Clothing getBed() {
    return bed;
  }

  public Boat<Boyfriend<Detective, Integer, Hunger<Jewelry>>> getLaw() {
    return law;
  }

  public Film<Deer<Shoal<Hardware, Crime, BigDecimal>, Chair<Cupboard, Bucket<Candy>>>> getMeat() {
    return meat;
  }

  public void setMeat(
      Film<Deer<Shoal<Hardware, Crime, BigDecimal>, Chair<Cupboard, Bucket<Candy>>>> meat) {
    this.meat = meat;
  }

  public Set<Butterfly> getHamburger() {
    return hamburger;
  }

  public void setHamburger(Set<Butterfly> hamburger) {
    this.hamburger = hamburger;
  }

  @Nonnull
  public Donkey<T> getImportance() {
    return importance;
  }

  public Long getRelaxation() {
    return relaxation;
  }

  public void setRelaxation(Long relaxation) {
    this.relaxation = relaxation;
  }

  @Nonnull
  public Joy getCooker() {
    return cooker;
  }

  public void setCooker(Joy cooker) {
    this.cooker = cooker;
  }

  @Nonnull
  public Equipment<Tiredness, Blood, Beauty> getJoy() {
    return joy;
  }

  public Blouse<String> getCar() {
    return car;
  }

  public void setCar(Blouse<String> car) {
    this.car = car;
  }

  public Cap getJam() {
    return jam;
  }

  @Nonnull
  public Boat<Teacup> getBunch() {
    return bunch;
  }

  public void setBunch(Boat<Teacup> bunch) {
    this.bunch = bunch;
  }

  public Cap getShower() {
    return shower;
  }

  public void setShower(Cap shower) {
    this.shower = shower;
  }

  @Nonnull
  public Botany getMaturity() {
    return maturity;
  }
}

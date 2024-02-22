package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;
import java.math.BigInteger;
import java.util.Map;

public class Hunger<T> {
  private final Distribution belief;
  private final Cupboard haircut;
  private final Cabinet film;
  private final Double courage;
  private final Crew<Honey<Orchestra>> everything;
  private final Crew<Map<Bow<Chaos>, Tiredness>> gang;
  private final Justice partnership;
  private final Daredevil luxury;
  private final Blood luck;
  private final Detective boyfriend;
  private final BigInteger handcuff;
  private final Short dancing;
  private final String countdown;
  private final Unreality<Awareness<Luxury>> bermudas;
  private final Honey<Alcohol<Teapot>> teacup;
  private Beans mob;
  private Integer importance;
  private Freedom<Bunch, Dresser<Boat<Bow<Speed>>>> need;
  private Bunch desktop;
  private String daydream;

  public Hunger(
      Distribution belief,
      Cupboard haircut,
      Cabinet film,
      Double courage,
      Crew<Honey<Orchestra>> everything,
      Crew<Map<Bow<Chaos>, Tiredness>> gang,
      Justice partnership,
      Daredevil luxury,
      Blood luck,
      Detective boyfriend,
      BigInteger handcuff,
      Short dancing,
      String countdown,
      Unreality<Awareness<Luxury>> bermudas,
      Honey<Alcohol<Teapot>> teacup) {
    this.belief = belief;
    this.haircut = haircut;
    this.film = film;
    this.courage = courage;
    this.everything = everything;
    this.gang = gang;
    this.partnership = partnership;
    this.luxury = luxury;
    this.luck = luck;
    this.boyfriend = boyfriend;
    this.handcuff = handcuff;
    this.dancing = dancing;
    this.countdown = countdown;
    this.bermudas = bermudas;
    this.teacup = teacup;
  }

  public Distribution getBelief() {
    return belief;
  }

  @Nonnull
  public Beans getMob() {
    return mob;
  }

  public void setMob(Beans mob) {
    this.mob = mob;
  }

  @Nonnull
  public Cupboard getHaircut() {
    return haircut;
  }

  public Cabinet getFilm() {
    return film;
  }

  @Nonnull
  public Double getCourage() {
    return courage;
  }

  @Nonnull
  public Integer getImportance() {
    return importance;
  }

  public void setImportance(Integer importance) {
    this.importance = importance;
  }

  @Nonnull
  public Crew<Honey<Orchestra>> getEverything() {
    return everything;
  }

  public Crew<Map<Bow<Chaos>, Tiredness>> getGang() {
    return gang;
  }

  public Freedom<Bunch, Dresser<Boat<Bow<Speed>>>> getNeed() {
    return need;
  }

  public void setNeed(Freedom<Bunch, Dresser<Boat<Bow<Speed>>>> need) {
    this.need = need;
  }

  @Nonnull
  public Justice getPartnership() {
    return partnership;
  }

  @Nonnull
  public Daredevil getLuxury() {
    return luxury;
  }

  @Nonnull
  public Bunch getDesktop() {
    return desktop;
  }

  public void setDesktop(Bunch desktop) {
    this.desktop = desktop;
  }

  public Blood getLuck() {
    return luck;
  }

  public Detective getBoyfriend() {
    return boyfriend;
  }

  @Nonnull
  public BigInteger getHandcuff() {
    return handcuff;
  }

  @Nonnull
  public Short getDancing() {
    return dancing;
  }

  @Nonnull
  public String getCountdown() {
    return countdown;
  }

  @Nonnull
  public String getDaydream() {
    return daydream;
  }

  public void setDaydream(String daydream) {
    this.daydream = daydream;
  }

  @Nonnull
  public Unreality<Awareness<Luxury>> getBermudas() {
    return bermudas;
  }

  public Honey<Alcohol<Teapot>> getTeacup() {
    return teacup;
  }
}

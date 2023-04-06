package com.cromoteca.parser.entities;

import dev.hilla.Nonnull;

public class Guest<T, U> {
  private final Hatred bed;
  private final Myself donkey;
  private final Pack dirt;
  private final T board;
  private final Long childhood;
  private final Skyscraper<Raincoat<Cleverness, Botany>, Cooker, Bed> fish;
  private final Shoal<Horsefly, Beans, Equipment<Detective, Photocopy<Partnership>, Cheeks>> flock;
  private Crowd<Bucket<Teacup>> law;
  private Wealth drum;
  private Coffee<Bed> hate;
  private Unreality<Aunt> awe;
  private Boolean luxury;
  private Dresser<Crime> airport;
  private Insanity linguistics;
  private Film<Handcuff> myself;

  public Guest(
      Hatred bed,
      Myself donkey,
      Pack dirt,
      T board,
      Long childhood,
      Skyscraper<Raincoat<Cleverness, Botany>, Cooker, Bed> fish,
      Shoal<Horsefly, Beans, Equipment<Detective, Photocopy<Partnership>, Cheeks>> flock) {
    this.bed = bed;
    this.donkey = donkey;
    this.dirt = dirt;
    this.board = board;
    this.childhood = childhood;
    this.fish = fish;
    this.flock = flock;
  }

  public Hatred getBed() {
    return bed;
  }

  public Crowd<Bucket<Teacup>> getLaw() {
    return law;
  }

  public void setLaw(Crowd<Bucket<Teacup>> law) {
    this.law = law;
  }

  public Myself getDonkey() {
    return donkey;
  }

  @Nonnull
  public Wealth getDrum() {
    return drum;
  }

  public void setDrum(Wealth drum) {
    this.drum = drum;
  }

  public Pack getDirt() {
    return dirt;
  }

  @Nonnull
  public Coffee<Bed> getHate() {
    return hate;
  }

  public void setHate(Coffee<Bed> hate) {
    this.hate = hate;
  }

  public Unreality<Aunt> getAwe() {
    return awe;
  }

  public void setAwe(Unreality<Aunt> awe) {
    this.awe = awe;
  }

  @Nonnull
  public T getBoard() {
    return board;
  }

  @Nonnull
  public Boolean getLuxury() {
    return luxury;
  }

  public void setLuxury(Boolean luxury) {
    this.luxury = luxury;
  }

  public Long getChildhood() {
    return childhood;
  }

  @Nonnull
  public Dresser<Crime> getAirport() {
    return airport;
  }

  public void setAirport(Dresser<Crime> airport) {
    this.airport = airport;
  }

  @Nonnull
  public Insanity getLinguistics() {
    return linguistics;
  }

  public void setLinguistics(Insanity linguistics) {
    this.linguistics = linguistics;
  }

  @Nonnull
  public Skyscraper<Raincoat<Cleverness, Botany>, Cooker, Bed> getFish() {
    return fish;
  }

  @Nonnull
  public Film<Handcuff> getMyself() {
    return myself;
  }

  public void setMyself(Film<Handcuff> myself) {
    this.myself = myself;
  }

  @Nonnull
  public Shoal<Horsefly, Beans, Equipment<Detective, Photocopy<Partnership>, Cheeks>> getFlock() {
    return flock;
  }
}

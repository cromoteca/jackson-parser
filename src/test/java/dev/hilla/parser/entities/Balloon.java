package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Balloon {
  private final Confidence<Butterfly, Homework> notebook;
  private final Boat<Psychology<Clothing, Equipment<Airline, Pack, String>>> donkey;
  private final List<Cupboard> jewelry;
  private final Distribution overdue;
  private final Byte honey;
  private final Love<Detective> teapot;
  private final Love<Clothing> desktop;
  private final Chaos nurture;
  private final Long houseboat;
  private final Detective innocence;
  private final Map<Loneliness, Satisfaction> book;
  private final Loneliness daydream;
  private final List<Luxury> armchair;
  private Love<Maturity<Crime>> horsefly;
  private Series<Distribution> film;
  private Byte psychology;
  private Access<Byte> guest;
  private Byte bacon;
  private Love<Background> football;
  private Character pile;
  private Freedom<BigDecimal, Detective> distribution;
  private Background wealth;

  public Balloon(
      Confidence<Butterfly, Homework> notebook,
      Boat<Psychology<Clothing, Equipment<Airline, Pack, String>>> donkey,
      List<Cupboard> jewelry,
      Distribution overdue,
      Byte honey,
      Love<Detective> teapot,
      Love<Clothing> desktop,
      Chaos nurture,
      Long houseboat,
      Detective innocence,
      Map<Loneliness, Satisfaction> book,
      Loneliness daydream,
      List<Luxury> armchair) {
    this.notebook = notebook;
    this.donkey = donkey;
    this.jewelry = jewelry;
    this.overdue = overdue;
    this.honey = honey;
    this.teapot = teapot;
    this.desktop = desktop;
    this.nurture = nurture;
    this.houseboat = houseboat;
    this.innocence = innocence;
    this.book = book;
    this.daydream = daydream;
    this.armchair = armchair;
  }

  @Nonnull
  public Love<Maturity<Crime>> getHorsefly() {
    return horsefly;
  }

  public void setHorsefly(Love<Maturity<Crime>> horsefly) {
    this.horsefly = horsefly;
  }

  public Confidence<Butterfly, Homework> getNotebook() {
    return notebook;
  }

  public Boat<Psychology<Clothing, Equipment<Airline, Pack, String>>> getDonkey() {
    return donkey;
  }

  @Nonnull
  public Series<Distribution> getFilm() {
    return film;
  }

  public void setFilm(Series<Distribution> film) {
    this.film = film;
  }

  @Nonnull
  public Byte getPsychology() {
    return psychology;
  }

  public void setPsychology(Byte psychology) {
    this.psychology = psychology;
  }

  public Access<Byte> getGuest() {
    return guest;
  }

  public void setGuest(Access<Byte> guest) {
    this.guest = guest;
  }

  public List<Cupboard> getJewelry() {
    return jewelry;
  }

  public Distribution getOverdue() {
    return overdue;
  }

  @Nonnull
  public Byte getBacon() {
    return bacon;
  }

  public void setBacon(Byte bacon) {
    this.bacon = bacon;
  }

  @Nonnull
  public Love<Background> getFootball() {
    return football;
  }

  public void setFootball(Love<Background> football) {
    this.football = football;
  }

  public Byte getHoney() {
    return honey;
  }

  public Love<Detective> getTeapot() {
    return teapot;
  }

  @Nonnull
  public Love<Clothing> getDesktop() {
    return desktop;
  }

  @Nonnull
  public Chaos getNurture() {
    return nurture;
  }

  @Nonnull
  public Long getHouseboat() {
    return houseboat;
  }

  @Nonnull
  public Detective getInnocence() {
    return innocence;
  }

  @Nonnull
  public Map<Loneliness, Satisfaction> getBook() {
    return book;
  }

  @Nonnull
  public Character getPile() {
    return pile;
  }

  public void setPile(Character pile) {
    this.pile = pile;
  }

  @Nonnull
  public Freedom<BigDecimal, Detective> getDistribution() {
    return distribution;
  }

  public void setDistribution(Freedom<BigDecimal, Detective> distribution) {
    this.distribution = distribution;
  }

  @Nonnull
  public Loneliness getDaydream() {
    return daydream;
  }

  @Nonnull
  public List<Luxury> getArmchair() {
    return armchair;
  }

  @Nonnull
  public Background getWealth() {
    return wealth;
  }

  public void setWealth(Background wealth) {
    this.wealth = wealth;
  }
}

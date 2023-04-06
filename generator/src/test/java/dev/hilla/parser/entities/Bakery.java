package dev.hilla.parser.entities;

import dev.hilla.Nonnull;
import java.util.List;
import java.util.Map;

public class Bakery<T> {
  private final Handcuff chest;
  private final Crime cabinet;
  private final Integer awareness;
  private final Botany crowd;
  private final Character troupe;
  private final Love<Homework> coffee;
  private final Character raincoat;
  private final Byte love;
  private Long photocopy;
  private Hallway<Crime> clarity;
  private Background union;
  private Homework teapot;
  private Series<Honey<Candy>> danger;
  private Map<List<Orchestra>, Homework> distribution;
  private Integer loneliness;
  private Float bridge;

  public Bakery(
      Handcuff chest,
      Crime cabinet,
      Integer awareness,
      Botany crowd,
      Character troupe,
      Love<Homework> coffee,
      Character raincoat,
      Byte love) {
    this.chest = chest;
    this.cabinet = cabinet;
    this.awareness = awareness;
    this.crowd = crowd;
    this.troupe = troupe;
    this.coffee = coffee;
    this.raincoat = raincoat;
    this.love = love;
  }

  @Nonnull
  public Handcuff getChest() {
    return chest;
  }

  public Crime getCabinet() {
    return cabinet;
  }

  @Nonnull
  public Integer getAwareness() {
    return awareness;
  }

  @Nonnull
  public Botany getCrowd() {
    return crowd;
  }

  public Character getTroupe() {
    return troupe;
  }

  public Long getPhotocopy() {
    return photocopy;
  }

  public void setPhotocopy(Long photocopy) {
    this.photocopy = photocopy;
  }

  @Nonnull
  public Love<Homework> getCoffee() {
    return coffee;
  }

  public Hallway<Crime> getClarity() {
    return clarity;
  }

  public void setClarity(Hallway<Crime> clarity) {
    this.clarity = clarity;
  }

  public Background getUnion() {
    return union;
  }

  public void setUnion(Background union) {
    this.union = union;
  }

  public Homework getTeapot() {
    return teapot;
  }

  public void setTeapot(Homework teapot) {
    this.teapot = teapot;
  }

  public Character getRaincoat() {
    return raincoat;
  }

  public Byte getLove() {
    return love;
  }

  public Series<Honey<Candy>> getDanger() {
    return danger;
  }

  public void setDanger(Series<Honey<Candy>> danger) {
    this.danger = danger;
  }

  public Map<List<Orchestra>, Homework> getDistribution() {
    return distribution;
  }

  public void setDistribution(Map<List<Orchestra>, Homework> distribution) {
    this.distribution = distribution;
  }

  public Integer getLoneliness() {
    return loneliness;
  }

  public void setLoneliness(Integer loneliness) {
    this.loneliness = loneliness;
  }

  public Float getBridge() {
    return bridge;
  }

  public void setBridge(Float bridge) {
    this.bridge = bridge;
  }
}

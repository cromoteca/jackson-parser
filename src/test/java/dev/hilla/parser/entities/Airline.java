package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;
import java.util.List;

public class Airline {
  private final List<Union> greenhouse;
  private final Love<Iron> hatred;
  private final Series<Satisfaction> overdue;
  private final Chair<Background, Bakery<Confidence<Honey<Long>, Candy>>> foot;
  private Pack justice;
  private Teacup importance;
  private Cap classmate;
  private Character moonlight;
  private Orchestra tiredness;
  private Cap baby;

  public Airline(
      List<Union> greenhouse,
      Love<Iron> hatred,
      Series<Satisfaction> overdue,
      Chair<Background, Bakery<Confidence<Honey<Long>, Candy>>> foot) {
    this.greenhouse = greenhouse;
    this.hatred = hatred;
    this.overdue = overdue;
    this.foot = foot;
  }

  @Nonnull
  public Pack getJustice() {
    return justice;
  }

  public void setJustice(Pack justice) {
    this.justice = justice;
  }

  @Nonnull
  public Teacup getImportance() {
    return importance;
  }

  public void setImportance(Teacup importance) {
    this.importance = importance;
  }

  @Nonnull
  public Cap getClassmate() {
    return classmate;
  }

  public void setClassmate(Cap classmate) {
    this.classmate = classmate;
  }

  public Character getMoonlight() {
    return moonlight;
  }

  public void setMoonlight(Character moonlight) {
    this.moonlight = moonlight;
  }

  @Nonnull
  public Orchestra getTiredness() {
    return tiredness;
  }

  public void setTiredness(Orchestra tiredness) {
    this.tiredness = tiredness;
  }

  @Nonnull
  public Cap getBaby() {
    return baby;
  }

  public void setBaby(Cap baby) {
    this.baby = baby;
  }

  public List<Union> getGreenhouse() {
    return greenhouse;
  }

  @Nonnull
  public Love<Iron> getHatred() {
    return hatred;
  }

  @Nonnull
  public Series<Satisfaction> getOverdue() {
    return overdue;
  }

  @Nonnull
  public Chair<Background, Bakery<Confidence<Honey<Long>, Candy>>> getFoot() {
    return foot;
  }
}

package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;
import java.util.Map;

public class Unreality<T> {
  private final Boat<Blouse<Loneliness>> unreality;
  private final Butterfly dentist;
  private final Film<Rattlesnake> fish;
  private final Justice countdown;
  private final Botany wariness;
  private final Cap heap;
  private Handcuff charlie;
  private Map<Satisfaction, Integer> courage;
  private Dresser<Countdown<Union>> bear;
  private Teacup fireworks;
  private Cleverness maturity;
  private Fame<Innocence<Union>, Balloon> wealth;

  public Unreality(
      Boat<Blouse<Loneliness>> unreality,
      Butterfly dentist,
      Film<Rattlesnake> fish,
      Justice countdown,
      Botany wariness,
      Cap heap) {
    this.unreality = unreality;
    this.dentist = dentist;
    this.fish = fish;
    this.countdown = countdown;
    this.wariness = wariness;
    this.heap = heap;
  }

  public Boat<Blouse<Loneliness>> getUnreality() {
    return unreality;
  }

  public Butterfly getDentist() {
    return dentist;
  }

  @Nonnull
  public Handcuff getCharlie() {
    return charlie;
  }

  public void setCharlie(Handcuff charlie) {
    this.charlie = charlie;
  }

  @Nonnull
  public Map<Satisfaction, Integer> getCourage() {
    return courage;
  }

  public void setCourage(Map<Satisfaction, Integer> courage) {
    this.courage = courage;
  }

  @Nonnull
  public Film<Rattlesnake> getFish() {
    return fish;
  }

  @Nonnull
  public Justice getCountdown() {
    return countdown;
  }

  @Nonnull
  public Dresser<Countdown<Union>> getBear() {
    return bear;
  }

  public void setBear(Dresser<Countdown<Union>> bear) {
    this.bear = bear;
  }

  public Teacup getFireworks() {
    return fireworks;
  }

  public void setFireworks(Teacup fireworks) {
    this.fireworks = fireworks;
  }

  public Cleverness getMaturity() {
    return maturity;
  }

  public void setMaturity(Cleverness maturity) {
    this.maturity = maturity;
  }

  @Nonnull
  public Botany getWariness() {
    return wariness;
  }

  @Nonnull
  public Cap getHeap() {
    return heap;
  }

  public Fame<Innocence<Union>, Balloon> getWealth() {
    return wealth;
  }

  public void setWealth(Fame<Innocence<Union>, Balloon> wealth) {
    this.wealth = wealth;
  }
}

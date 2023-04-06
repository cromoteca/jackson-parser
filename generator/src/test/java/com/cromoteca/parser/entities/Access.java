package com.cromoteca.parser.entities;

import dev.hilla.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Access<T> {
  private final Map<Hallway<Detective>, Float> cap;
  private final Set<BigDecimal> glass;
  private final List<Equipment<List<Character>, Detective, List<Series<Detective>>>> compassion;
  private final Byte iron;
  private final Principle<Long> adulthood;
  private List<Series<Character>> fan;
  private Character chopstick;
  private Float detective;
  private Blouse<Double> battlefield;

  public Access(
      Map<Hallway<Detective>, Float> cap,
      Set<BigDecimal> glass,
      List<Equipment<List<Character>, Detective, List<Series<Detective>>>> compassion,
      Byte iron,
      Principle<Long> adulthood) {
    this.cap = cap;
    this.glass = glass;
    this.compassion = compassion;
    this.iron = iron;
    this.adulthood = adulthood;
  }

  public List<Series<Character>> getFan() {
    return fan;
  }

  public void setFan(List<Series<Character>> fan) {
    this.fan = fan;
  }

  @Nonnull
  public Map<Hallway<Detective>, Float> getCap() {
    return cap;
  }

  @Nonnull
  public Set<BigDecimal> getGlass() {
    return glass;
  }

  public List<Equipment<List<Character>, Detective, List<Series<Detective>>>> getCompassion() {
    return compassion;
  }

  @Nonnull
  public Character getChopstick() {
    return chopstick;
  }

  public void setChopstick(Character chopstick) {
    this.chopstick = chopstick;
  }

  public Byte getIron() {
    return iron;
  }

  public Principle<Long> getAdulthood() {
    return adulthood;
  }

  @Nonnull
  public Float getDetective() {
    return detective;
  }

  public void setDetective(Float detective) {
    this.detective = detective;
  }

  @Nonnull
  public Blouse<Double> getBattlefield() {
    return battlefield;
  }

  public void setBattlefield(Blouse<Double> battlefield) {
    this.battlefield = battlefield;
  }
}

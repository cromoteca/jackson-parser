package com.cromoteca.entities;

import dev.hilla.Nonnull;
import java.util.List;

public class Light<T, U> {
  private final Charlie courage;
  private List<Satisfaction> insanity;
  private Byte flag;
  private T aircraft;
  private Beauty wariness;

  public Light(Charlie courage) {
    this.courage = courage;
  }

  @Nonnull
  public Charlie getCourage() {
    return courage;
  }

  @Nonnull
  public List<Satisfaction> getInsanity() {
    return insanity;
  }

  public void setInsanity(List<Satisfaction> insanity) {
    this.insanity = insanity;
  }

  public Byte getFlag() {
    return flag;
  }

  public void setFlag(Byte flag) {
    this.flag = flag;
  }

  public T getAircraft() {
    return aircraft;
  }

  public void setAircraft(T aircraft) {
    this.aircraft = aircraft;
  }

  public Beauty getWariness() {
    return wariness;
  }

  public void setWariness(Beauty wariness) {
    this.wariness = wariness;
  }
}

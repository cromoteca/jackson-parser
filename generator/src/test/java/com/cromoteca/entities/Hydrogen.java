package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;

public class Hydrogen {
  private final Equipment<Tiredness, Cow<Book>, Double> countdown;
  private Long danger;

  public Hydrogen(Equipment<Tiredness, Cow<Book>, Double> countdown) {
    this.countdown = countdown;
  }

  public Long getDanger() {
    return danger;
  }

  public void setDanger(Long danger) {
    this.danger = danger;
  }

  @Nonnull
  public Equipment<Tiredness, Cow<Book>, Double> getCountdown() {
    return countdown;
  }
}

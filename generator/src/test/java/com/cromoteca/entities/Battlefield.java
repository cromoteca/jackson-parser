package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;

public class Battlefield {
  private final Cupboard jewelry;
  private final Fall adulthood;

  public Battlefield(Cupboard jewelry, Fall adulthood) {
    this.jewelry = jewelry;
    this.adulthood = adulthood;
  }

  @Nonnull
  public Cupboard getJewelry() {
    return jewelry;
  }

  public Fall getAdulthood() {
    return adulthood;
  }
}

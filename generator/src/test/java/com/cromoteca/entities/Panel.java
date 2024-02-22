package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;

public class Panel {
  private final Cupboard overdue;
  private final Series<Sanity<Float>> liberty;
  private Opinion<Thunderstorm> bear;

  public Panel(Cupboard overdue, Series<Sanity<Float>> liberty) {
    this.overdue = overdue;
    this.liberty = liberty;
  }

  @Nonnull
  public Opinion<Thunderstorm> getBear() {
    return bear;
  }

  public void setBear(Opinion<Thunderstorm> bear) {
    this.bear = bear;
  }

  public Cupboard getOverdue() {
    return overdue;
  }

  @Nonnull
  public Series<Sanity<Float>> getLiberty() {
    return liberty;
  }
}

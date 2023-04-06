package com.cromoteca.parser.entities;

import dev.hilla.Nonnull;
import java.util.Set;

public class Reality<T> {
  private final Stack footprint;
  private final Cabinet designer;
  private final Shower fan;
  private final Set<Jam<Cleverness, Bakery<Handcuff>>> kindness;
  private final Union everybody;
  private final Cooker hallway;
  private Compassion chest;
  private Fireworks bow;

  public Reality(
      Stack footprint,
      Cabinet designer,
      Shower fan,
      Set<Jam<Cleverness, Bakery<Handcuff>>> kindness,
      Union everybody,
      Cooker hallway) {
    this.footprint = footprint;
    this.designer = designer;
    this.fan = fan;
    this.kindness = kindness;
    this.everybody = everybody;
    this.hallway = hallway;
  }

  @Nonnull
  public Stack getFootprint() {
    return footprint;
  }

  public Cabinet getDesigner() {
    return designer;
  }

  @Nonnull
  public Compassion getChest() {
    return chest;
  }

  public void setChest(Compassion chest) {
    this.chest = chest;
  }

  @Nonnull
  public Shower getFan() {
    return fan;
  }

  @Nonnull
  public Fireworks getBow() {
    return bow;
  }

  public void setBow(Fireworks bow) {
    this.bow = bow;
  }

  public Set<Jam<Cleverness, Bakery<Handcuff>>> getKindness() {
    return kindness;
  }

  @Nonnull
  public Union getEverybody() {
    return everybody;
  }

  public Cooker getHallway() {
    return hallway;
  }
}

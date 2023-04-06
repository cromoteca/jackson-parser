package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

public class Herd {
  private final Teapot group;
  private final Pack everything;
  private Flag<Myself> dragonfly;
  private Designer contentment;
  private Clarity<Integer, Relaxation> skyscraper;

  public Herd(Teapot group, Pack everything) {
    this.group = group;
    this.everything = everything;
  }

  public Teapot getGroup() {
    return group;
  }

  public Pack getEverything() {
    return everything;
  }

  @Nonnull
  public Flag<Myself> getDragonfly() {
    return dragonfly;
  }

  public void setDragonfly(Flag<Myself> dragonfly) {
    this.dragonfly = dragonfly;
  }

  @Nonnull
  public Designer getContentment() {
    return contentment;
  }

  public void setContentment(Designer contentment) {
    this.contentment = contentment;
  }

  public Clarity<Integer, Relaxation> getSkyscraper() {
    return skyscraper;
  }

  public void setSkyscraper(Clarity<Integer, Relaxation> skyscraper) {
    this.skyscraper = skyscraper;
  }
}

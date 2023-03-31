package dev.hilla.parser.entities;

public class Flag<T> {
  private Loneliness opinion;
  private Cupboard armchair;

  public Flag() {}

  public Loneliness getOpinion() {
    return opinion;
  }

  public void setOpinion(Loneliness opinion) {
    this.opinion = opinion;
  }

  public Cupboard getArmchair() {
    return armchair;
  }

  public void setArmchair(Cupboard armchair) {
    this.armchair = armchair;
  }
}

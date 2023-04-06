package com.cromoteca.parser.entities;

import dev.hilla.Nonnull;
import java.math.BigDecimal;
import java.util.Map;

public class Hallway<T> {
  private final Orchestra contentment;
  private final Detective innocence;
  private final Orchestra liberty;
  private Loneliness dresser;
  private Detective background;
  private Boolean staff;
  private Map<Byte, Orchestra> hate;
  private BigDecimal dancing;
  private Pack greenhouse;

  public Hallway(Orchestra contentment, Detective innocence, Orchestra liberty) {
    this.contentment = contentment;
    this.innocence = innocence;
    this.liberty = liberty;
  }

  @Nonnull
  public Loneliness getDresser() {
    return dresser;
  }

  public void setDresser(Loneliness dresser) {
    this.dresser = dresser;
  }

  public Detective getBackground() {
    return background;
  }

  public void setBackground(Detective background) {
    this.background = background;
  }

  @Nonnull
  public Orchestra getContentment() {
    return contentment;
  }

  public Detective getInnocence() {
    return innocence;
  }

  public Boolean getStaff() {
    return staff;
  }

  public void setStaff(Boolean staff) {
    this.staff = staff;
  }

  public Map<Byte, Orchestra> getHate() {
    return hate;
  }

  public void setHate(Map<Byte, Orchestra> hate) {
    this.hate = hate;
  }

  @Nonnull
  public BigDecimal getDancing() {
    return dancing;
  }

  public void setDancing(BigDecimal dancing) {
    this.dancing = dancing;
  }

  @Nonnull
  public Pack getGreenhouse() {
    return greenhouse;
  }

  public void setGreenhouse(Pack greenhouse) {
    this.greenhouse = greenhouse;
  }

  @Nonnull
  public Orchestra getLiberty() {
    return liberty;
  }
}

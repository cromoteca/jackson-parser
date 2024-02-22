package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;
import java.math.BigInteger;

public class Sandcastle {
  private final Character blouse;
  private final Orchestra trend;
  private BigInteger group;
  private Alcohol<Hair> opinion;
  private Sunshine homework;

  public Sandcastle(Character blouse, Orchestra trend) {
    this.blouse = blouse;
    this.trend = trend;
  }

  @Nonnull
  public BigInteger getGroup() {
    return group;
  }

  public void setGroup(BigInteger group) {
    this.group = group;
  }

  @Nonnull
  public Alcohol<Hair> getOpinion() {
    return opinion;
  }

  public void setOpinion(Alcohol<Hair> opinion) {
    this.opinion = opinion;
  }

  public Character getBlouse() {
    return blouse;
  }

  public Orchestra getTrend() {
    return trend;
  }

  public Sunshine getHomework() {
    return homework;
  }

  public void setHomework(Sunshine homework) {
    this.homework = homework;
  }
}

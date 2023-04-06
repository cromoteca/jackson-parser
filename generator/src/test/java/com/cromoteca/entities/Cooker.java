package com.cromoteca.entities;

import dev.hilla.Nonnull;
import java.math.BigDecimal;

public class Cooker {
  private final Crowd<Snowboard> series;
  private final Photocopy<BigDecimal> principle;
  private final Awe deer;
  private Homework mob;
  private Growth unreality;
  private Bakery<Rattlesnake> dentist;
  private Sanity<Wealth> duty;
  private Cupboard overdue;
  private Integer teacup;
  private Pack heap;

  public Cooker(Crowd<Snowboard> series, Photocopy<BigDecimal> principle, Awe deer) {
    this.series = series;
    this.principle = principle;
    this.deer = deer;
  }

  @Nonnull
  public Homework getMob() {
    return mob;
  }

  public void setMob(Homework mob) {
    this.mob = mob;
  }

  public Crowd<Snowboard> getSeries() {
    return series;
  }

  @Nonnull
  public Growth getUnreality() {
    return unreality;
  }

  public void setUnreality(Growth unreality) {
    this.unreality = unreality;
  }

  public Bakery<Rattlesnake> getDentist() {
    return dentist;
  }

  public void setDentist(Bakery<Rattlesnake> dentist) {
    this.dentist = dentist;
  }

  public Sanity<Wealth> getDuty() {
    return duty;
  }

  public void setDuty(Sanity<Wealth> duty) {
    this.duty = duty;
  }

  @Nonnull
  public Photocopy<BigDecimal> getPrinciple() {
    return principle;
  }

  @Nonnull
  public Cupboard getOverdue() {
    return overdue;
  }

  public void setOverdue(Cupboard overdue) {
    this.overdue = overdue;
  }

  public Integer getTeacup() {
    return teacup;
  }

  public void setTeacup(Integer teacup) {
    this.teacup = teacup;
  }

  @Nonnull
  public Awe getDeer() {
    return deer;
  }

  public Pack getHeap() {
    return heap;
  }

  public void setHeap(Pack heap) {
    this.heap = heap;
  }
}

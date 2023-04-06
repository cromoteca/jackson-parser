package com.cromoteca.entities;

import dev.hilla.Nonnull;

public class Group<T> {
  private final Horsefly teardrop;
  private final Aircraft<Honey<Teardrop>> iron;
  private Cupboard horsefly;
  private Freezer comeback;
  private Long love;
  private Greenhouse<Calm<Book>, Bow<Countdown<Aunt>>> blood;

  public Group(Horsefly teardrop, Aircraft<Honey<Teardrop>> iron) {
    this.teardrop = teardrop;
    this.iron = iron;
  }

  public Cupboard getHorsefly() {
    return horsefly;
  }

  public void setHorsefly(Cupboard horsefly) {
    this.horsefly = horsefly;
  }

  @Nonnull
  public Horsefly getTeardrop() {
    return teardrop;
  }

  @Nonnull
  public Freezer getComeback() {
    return comeback;
  }

  public void setComeback(Freezer comeback) {
    this.comeback = comeback;
  }

  @Nonnull
  public Long getLove() {
    return love;
  }

  public void setLove(Long love) {
    this.love = love;
  }

  @Nonnull
  public Aircraft<Honey<Teardrop>> getIron() {
    return iron;
  }

  @Nonnull
  public Greenhouse<Calm<Book>, Bow<Countdown<Aunt>>> getBlood() {
    return blood;
  }

  public void setBlood(Greenhouse<Calm<Book>, Bow<Countdown<Aunt>>> blood) {
    this.blood = blood;
  }
}

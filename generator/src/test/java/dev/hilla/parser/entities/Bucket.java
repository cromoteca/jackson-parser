package dev.hilla.parser.entities;

import dev.hilla.Nonnull;
import java.math.BigInteger;
import java.util.Set;

public class Bucket<T> {
  private final Hatred frog;
  private final Double drum;
  private final Fame<Detective, Strenght<Set<Cleverness>>> access;
  private final Character hatred;
  private Cleverness ball;
  private Blouse<Sanity<Love<Double>>> honey;
  private Equipment<BigInteger, Detective, Access<Long>> dentist;
  private Detective danger;
  private Detective compassion;
  private Satisfaction snowboard;
  private Blood blood;

  public Bucket(
      Hatred frog,
      Double drum,
      Fame<Detective, Strenght<Set<Cleverness>>> access,
      Character hatred) {
    this.frog = frog;
    this.drum = drum;
    this.access = access;
    this.hatred = hatred;
  }

  @Nonnull
  public Cleverness getBall() {
    return ball;
  }

  public void setBall(Cleverness ball) {
    this.ball = ball;
  }

  @Nonnull
  public Blouse<Sanity<Love<Double>>> getHoney() {
    return honey;
  }

  public void setHoney(Blouse<Sanity<Love<Double>>> honey) {
    this.honey = honey;
  }

  @Nonnull
  public Hatred getFrog() {
    return frog;
  }

  public Equipment<BigInteger, Detective, Access<Long>> getDentist() {
    return dentist;
  }

  public void setDentist(Equipment<BigInteger, Detective, Access<Long>> dentist) {
    this.dentist = dentist;
  }

  public Double getDrum() {
    return drum;
  }

  @Nonnull
  public Detective getDanger() {
    return danger;
  }

  public void setDanger(Detective danger) {
    this.danger = danger;
  }

  @Nonnull
  public Fame<Detective, Strenght<Set<Cleverness>>> getAccess() {
    return access;
  }

  @Nonnull
  public Detective getCompassion() {
    return compassion;
  }

  public void setCompassion(Detective compassion) {
    this.compassion = compassion;
  }

  public Character getHatred() {
    return hatred;
  }

  public Satisfaction getSnowboard() {
    return snowboard;
  }

  public void setSnowboard(Satisfaction snowboard) {
    this.snowboard = snowboard;
  }

  @Nonnull
  public Blood getBlood() {
    return blood;
  }

  public void setBlood(Blood blood) {
    this.blood = blood;
  }
}

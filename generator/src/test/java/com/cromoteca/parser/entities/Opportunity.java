package com.cromoteca.parser.entities;

import dev.hilla.Nonnull;

public class Opportunity {
  private final Danger<Cap, Float, Crime> ball;
  private final Speed access;
  private final Book freedom;
  private final Adulthood beans;
  private final Bridge luxury;
  private final Partnership charlie;
  private Dresser<Thunderstorm> yourself;
  private Coffee<Joy> sunshine;
  private Nurture grass;
  private Adulthood foot;
  private Daredevil inflation;
  private Jam<Classmate, Bucket<Clothing>> duty;
  private Hunger<Board<Butterfly>> danger;
  private Bridge shower;
  private Stack victory;
  private Coffee<Dresser<Butterfly>> cleverness;

  public Opportunity(
      Danger<Cap, Float, Crime> ball,
      Speed access,
      Book freedom,
      Adulthood beans,
      Bridge luxury,
      Partnership charlie) {
    this.ball = ball;
    this.access = access;
    this.freedom = freedom;
    this.beans = beans;
    this.luxury = luxury;
    this.charlie = charlie;
  }

  public Danger<Cap, Float, Crime> getBall() {
    return ball;
  }

  @Nonnull
  public Dresser<Thunderstorm> getYourself() {
    return yourself;
  }

  public void setYourself(Dresser<Thunderstorm> yourself) {
    this.yourself = yourself;
  }

  public Speed getAccess() {
    return access;
  }

  public Coffee<Joy> getSunshine() {
    return sunshine;
  }

  public void setSunshine(Coffee<Joy> sunshine) {
    this.sunshine = sunshine;
  }

  @Nonnull
  public Nurture getGrass() {
    return grass;
  }

  public void setGrass(Nurture grass) {
    this.grass = grass;
  }

  public Book getFreedom() {
    return freedom;
  }

  @Nonnull
  public Adulthood getFoot() {
    return foot;
  }

  public void setFoot(Adulthood foot) {
    this.foot = foot;
  }

  public Adulthood getBeans() {
    return beans;
  }

  @Nonnull
  public Daredevil getInflation() {
    return inflation;
  }

  public void setInflation(Daredevil inflation) {
    this.inflation = inflation;
  }

  @Nonnull
  public Bridge getLuxury() {
    return luxury;
  }

  public Jam<Classmate, Bucket<Clothing>> getDuty() {
    return duty;
  }

  public void setDuty(Jam<Classmate, Bucket<Clothing>> duty) {
    this.duty = duty;
  }

  @Nonnull
  public Hunger<Board<Butterfly>> getDanger() {
    return danger;
  }

  public void setDanger(Hunger<Board<Butterfly>> danger) {
    this.danger = danger;
  }

  public Partnership getCharlie() {
    return charlie;
  }

  public Bridge getShower() {
    return shower;
  }

  public void setShower(Bridge shower) {
    this.shower = shower;
  }

  public Stack getVictory() {
    return victory;
  }

  public void setVictory(Stack victory) {
    this.victory = victory;
  }

  @Nonnull
  public Coffee<Dresser<Butterfly>> getCleverness() {
    return cleverness;
  }

  public void setCleverness(Coffee<Dresser<Butterfly>> cleverness) {
    this.cleverness = cleverness;
  }
}

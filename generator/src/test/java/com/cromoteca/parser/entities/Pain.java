package com.cromoteca.parser.entities;

import dev.hilla.Nonnull;
import java.util.Set;

public class Pain {
  private final Principle<Homework> childhood;
  private final Cat troupe;
  private final Alcohol<Double> shower;
  private final Set<Cooker> bridge;
  private Charlie swarm;
  private Chest speed;
  private Everything bucket;
  private Freedom<Panel, String> cat;
  private Blouse<Ball> bakery;
  private Airline sunshine;

  public Pain(
      Principle<Homework> childhood, Cat troupe, Alcohol<Double> shower, Set<Cooker> bridge) {
    this.childhood = childhood;
    this.troupe = troupe;
    this.shower = shower;
    this.bridge = bridge;
  }

  public Charlie getSwarm() {
    return swarm;
  }

  public void setSwarm(Charlie swarm) {
    this.swarm = swarm;
  }

  public Chest getSpeed() {
    return speed;
  }

  public void setSpeed(Chest speed) {
    this.speed = speed;
  }

  public Everything getBucket() {
    return bucket;
  }

  public void setBucket(Everything bucket) {
    this.bucket = bucket;
  }

  public Principle<Homework> getChildhood() {
    return childhood;
  }

  @Nonnull
  public Freedom<Panel, String> getCat() {
    return cat;
  }

  public void setCat(Freedom<Panel, String> cat) {
    this.cat = cat;
  }

  public Blouse<Ball> getBakery() {
    return bakery;
  }

  public void setBakery(Blouse<Ball> bakery) {
    this.bakery = bakery;
  }

  public Cat getTroupe() {
    return troupe;
  }

  @Nonnull
  public Airline getSunshine() {
    return sunshine;
  }

  public void setSunshine(Airline sunshine) {
    this.sunshine = sunshine;
  }

  public Alcohol<Double> getShower() {
    return shower;
  }

  public Set<Cooker> getBridge() {
    return bridge;
  }
}

package com.cromoteca.entities;

import dev.hilla.Nonnull;
import java.util.List;
import java.util.Map;

public class Raincoat<T, U> {
  private final Homework music;
  private final Blouse<Crowd<Blood>> cupboard;
  private final Principle<Access<Stack>> opportunity;
  private final Psychology<Map<Glass<Book>, Countdown<Joy>>, List<Photocopy<Orchestra>>> data;
  private final Meat<Butterfly, Flag<Chest>> liberty;
  private final Candy bacon;
  private Bunch belief;
  private Golf swarm;
  private Photocopy<Homework> law;
  private Beans fish;
  private Iron fireworks;

  public Raincoat(
      Homework music,
      Blouse<Crowd<Blood>> cupboard,
      Principle<Access<Stack>> opportunity,
      Psychology<Map<Glass<Book>, Countdown<Joy>>, List<Photocopy<Orchestra>>> data,
      Meat<Butterfly, Flag<Chest>> liberty,
      Candy bacon) {
    this.music = music;
    this.cupboard = cupboard;
    this.opportunity = opportunity;
    this.data = data;
    this.liberty = liberty;
    this.bacon = bacon;
  }

  public Bunch getBelief() {
    return belief;
  }

  public void setBelief(Bunch belief) {
    this.belief = belief;
  }

  public Golf getSwarm() {
    return swarm;
  }

  public void setSwarm(Golf swarm) {
    this.swarm = swarm;
  }

  @Nonnull
  public Photocopy<Homework> getLaw() {
    return law;
  }

  public void setLaw(Photocopy<Homework> law) {
    this.law = law;
  }

  @Nonnull
  public Homework getMusic() {
    return music;
  }

  @Nonnull
  public Blouse<Crowd<Blood>> getCupboard() {
    return cupboard;
  }

  public Principle<Access<Stack>> getOpportunity() {
    return opportunity;
  }

  @Nonnull
  public Beans getFish() {
    return fish;
  }

  public void setFish(Beans fish) {
    this.fish = fish;
  }

  public Psychology<Map<Glass<Book>, Countdown<Joy>>, List<Photocopy<Orchestra>>> getData() {
    return data;
  }

  @Nonnull
  public Meat<Butterfly, Flag<Chest>> getLiberty() {
    return liberty;
  }

  public Candy getBacon() {
    return bacon;
  }

  @Nonnull
  public Iron getFireworks() {
    return fireworks;
  }

  public void setFireworks(Iron fireworks) {
    this.fireworks = fireworks;
  }
}

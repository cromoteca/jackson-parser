package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Teacup {
  private final List<Insanity> aunt;
  private final Pack film;
  private final Pack inside;
  private final Double everybody;
  private final Cupboard heap;
  private final Integer relaxation;
  private final Satisfaction everything;
  private final Map<Series<Map<Principle<Orchestra>, Integer>>, Float> blender;
  private final List<
          Map<Hallway<Bakery<List<Candy>>>, Principle<Hallway<List<Map<BigInteger, Candy>>>>>>
      candy;
  private final Map<Botany, Boat<Maturity<Long>>> hardware;
  private final Homework greenhouse;
  private Insanity frog;
  private Pack ringworm;
  private Background blood;
  private Freedom<Long, Maturity<Access<Insanity>>> honey;
  private Freedom<Freedom<Rattlesnake, Bakery<Character>>, Botany> unreality;
  private Set<Teapot> bunch;
  private Boolean pack;

  public Teacup(
      List<Insanity> aunt,
      Pack film,
      Pack inside,
      Double everybody,
      Cupboard heap,
      Integer relaxation,
      Satisfaction everything,
      Map<Series<Map<Principle<Orchestra>, Integer>>, Float> blender,
      List<Map<Hallway<Bakery<List<Candy>>>, Principle<Hallway<List<Map<BigInteger, Candy>>>>>>
          candy,
      Map<Botany, Boat<Maturity<Long>>> hardware,
      Homework greenhouse) {
    this.aunt = aunt;
    this.film = film;
    this.inside = inside;
    this.everybody = everybody;
    this.heap = heap;
    this.relaxation = relaxation;
    this.everything = everything;
    this.blender = blender;
    this.candy = candy;
    this.hardware = hardware;
    this.greenhouse = greenhouse;
  }

  public List<Insanity> getAunt() {
    return aunt;
  }

  @Nonnull
  public Pack getFilm() {
    return film;
  }

  @Nonnull
  public Insanity getFrog() {
    return frog;
  }

  public void setFrog(Insanity frog) {
    this.frog = frog;
  }

  public Pack getRingworm() {
    return ringworm;
  }

  public void setRingworm(Pack ringworm) {
    this.ringworm = ringworm;
  }

  @Nonnull
  public Pack getInside() {
    return inside;
  }

  @Nonnull
  public Double getEverybody() {
    return everybody;
  }

  @Nonnull
  public Background getBlood() {
    return blood;
  }

  public void setBlood(Background blood) {
    this.blood = blood;
  }

  @Nonnull
  public Cupboard getHeap() {
    return heap;
  }

  @Nonnull
  public Integer getRelaxation() {
    return relaxation;
  }

  @Nonnull
  public Freedom<Long, Maturity<Access<Insanity>>> getHoney() {
    return honey;
  }

  public void setHoney(Freedom<Long, Maturity<Access<Insanity>>> honey) {
    this.honey = honey;
  }

  public Satisfaction getEverything() {
    return everything;
  }

  @Nonnull
  public Freedom<Freedom<Rattlesnake, Bakery<Character>>, Botany> getUnreality() {
    return unreality;
  }

  public void setUnreality(Freedom<Freedom<Rattlesnake, Bakery<Character>>, Botany> unreality) {
    this.unreality = unreality;
  }

  @Nonnull
  public Map<Series<Map<Principle<Orchestra>, Integer>>, Float> getBlender() {
    return blender;
  }

  @Nonnull
  public Set<Teapot> getBunch() {
    return bunch;
  }

  public void setBunch(Set<Teapot> bunch) {
    this.bunch = bunch;
  }

  public List<Map<Hallway<Bakery<List<Candy>>>, Principle<Hallway<List<Map<BigInteger, Candy>>>>>>
      getCandy() {
    return candy;
  }

  public Map<Botany, Boat<Maturity<Long>>> getHardware() {
    return hardware;
  }

  @Nonnull
  public Homework getGreenhouse() {
    return greenhouse;
  }

  @Nonnull
  public Boolean getPack() {
    return pack;
  }

  public void setPack(Boolean pack) {
    this.pack = pack;
  }
}

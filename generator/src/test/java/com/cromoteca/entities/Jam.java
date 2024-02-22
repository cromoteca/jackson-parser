package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;
import java.util.List;

public class Jam<T, U> {
  private final Character opinion;
  private final Float photocopy;
  private final Union detective;
  private final Series<Bakery<Rattlesnake>> thunderstorm;
  private final Background football;
  private final Bakery<Cap> awe;
  private final Hate heap;
  private final Homework stack;
  private final Charlie industry;
  private final Luxury duty;
  private final Teapot compassion;
  private final Pile countdown;
  private final Blouse<Tiredness> myself;
  private final Strenght<Hatred> hatred;
  private Cap law;
  private Dresser<Sanity<Clothing>> liberty;
  private Background panel;
  private Boat<Maturity<List<Hallway<Daredevil>>>> dresser;
  private Long fame;
  private Candy pain;
  private Hatred briefcase;
  private Integer raincoat;
  private Pile pile;
  private Pack boat;

  public Jam(
      Character opinion,
      Float photocopy,
      Union detective,
      Series<Bakery<Rattlesnake>> thunderstorm,
      Background football,
      Bakery<Cap> awe,
      Hate heap,
      Homework stack,
      Charlie industry,
      Luxury duty,
      Teapot compassion,
      Pile countdown,
      Blouse<Tiredness> myself,
      Strenght<Hatred> hatred) {
    this.opinion = opinion;
    this.photocopy = photocopy;
    this.detective = detective;
    this.thunderstorm = thunderstorm;
    this.football = football;
    this.awe = awe;
    this.heap = heap;
    this.stack = stack;
    this.industry = industry;
    this.duty = duty;
    this.compassion = compassion;
    this.countdown = countdown;
    this.myself = myself;
    this.hatred = hatred;
  }

  public Cap getLaw() {
    return law;
  }

  public void setLaw(Cap law) {
    this.law = law;
  }

  public Character getOpinion() {
    return opinion;
  }

  @Nonnull
  public Float getPhotocopy() {
    return photocopy;
  }

  public Dresser<Sanity<Clothing>> getLiberty() {
    return liberty;
  }

  public void setLiberty(Dresser<Sanity<Clothing>> liberty) {
    this.liberty = liberty;
  }

  @Nonnull
  public Union getDetective() {
    return detective;
  }

  public Series<Bakery<Rattlesnake>> getThunderstorm() {
    return thunderstorm;
  }

  @Nonnull
  public Background getFootball() {
    return football;
  }

  @Nonnull
  public Bakery<Cap> getAwe() {
    return awe;
  }

  public Hate getHeap() {
    return heap;
  }

  @Nonnull
  public Homework getStack() {
    return stack;
  }

  @Nonnull
  public Charlie getIndustry() {
    return industry;
  }

  @Nonnull
  public Background getPanel() {
    return panel;
  }

  public void setPanel(Background panel) {
    this.panel = panel;
  }

  @Nonnull
  public Boat<Maturity<List<Hallway<Daredevil>>>> getDresser() {
    return dresser;
  }

  public void setDresser(Boat<Maturity<List<Hallway<Daredevil>>>> dresser) {
    this.dresser = dresser;
  }

  @Nonnull
  public Long getFame() {
    return fame;
  }

  public void setFame(Long fame) {
    this.fame = fame;
  }

  @Nonnull
  public Candy getPain() {
    return pain;
  }

  public void setPain(Candy pain) {
    this.pain = pain;
  }

  @Nonnull
  public Hatred getBriefcase() {
    return briefcase;
  }

  public void setBriefcase(Hatred briefcase) {
    this.briefcase = briefcase;
  }

  public Integer getRaincoat() {
    return raincoat;
  }

  public void setRaincoat(Integer raincoat) {
    this.raincoat = raincoat;
  }

  public Luxury getDuty() {
    return duty;
  }

  @Nonnull
  public Pile getPile() {
    return pile;
  }

  public void setPile(Pile pile) {
    this.pile = pile;
  }

  @Nonnull
  public Teapot getCompassion() {
    return compassion;
  }

  @Nonnull
  public Pile getCountdown() {
    return countdown;
  }

  public Blouse<Tiredness> getMyself() {
    return myself;
  }

  public Strenght<Hatred> getHatred() {
    return hatred;
  }

  public Pack getBoat() {
    return boat;
  }

  public void setBoat(Pack boat) {
    this.boat = boat;
  }
}

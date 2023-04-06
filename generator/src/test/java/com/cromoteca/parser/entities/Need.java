package com.cromoteca.parser.entities;

import dev.hilla.Nonnull;

public class Need {
  private final Thunderstorm chair;
  private final Bridge strenght;
  private final Psychology<Cooker, Honey<Pancake>> drum;
  private final Psychology<Orchestra, Education<Herd, Grass<Gang>>> grass;
  private final Hardware liberty;
  private final Crowd<Insanity> thunderstorm;
  private final Tolerance<Rattlesnake> coffee;
  private final Rattlesnake importance;
  private final Detective duty;
  private final Flock<Hydrogen, Deer<Awe, Float>> linguistics;
  private Frog calm;
  private Skyscraper<
          Belief<
              Armchair<Bucket<Hair>, Skyscraper<Hate, Speed, Confidence<Float, Double>>, Teacup>,
              Desk>,
          Innocence<Trend<Blender<Balloon>>>,
          Shower>
      ringworm;
  private Boolean blood;
  private Timetable cap;
  private Dancing classmate;
  private Myself education;
  private Education<Data, Union> love;
  private Guest<Union, Overdue<Freedom<Car, Gang>>> blender;
  private Crowd<Opinion<Jealousy>> opportunity;
  private Ringworm fall;
  private Background fireworks;

  public Need(
      Thunderstorm chair,
      Bridge strenght,
      Psychology<Cooker, Honey<Pancake>> drum,
      Psychology<Orchestra, Education<Herd, Grass<Gang>>> grass,
      Hardware liberty,
      Crowd<Insanity> thunderstorm,
      Tolerance<Rattlesnake> coffee,
      Rattlesnake importance,
      Detective duty,
      Flock<Hydrogen, Deer<Awe, Float>> linguistics) {
    this.chair = chair;
    this.strenght = strenght;
    this.drum = drum;
    this.grass = grass;
    this.liberty = liberty;
    this.thunderstorm = thunderstorm;
    this.coffee = coffee;
    this.importance = importance;
    this.duty = duty;
    this.linguistics = linguistics;
  }

  public Frog getCalm() {
    return calm;
  }

  public void setCalm(Frog calm) {
    this.calm = calm;
  }

  public Skyscraper<
          Belief<
              Armchair<Bucket<Hair>, Skyscraper<Hate, Speed, Confidence<Float, Double>>, Teacup>,
              Desk>,
          Innocence<Trend<Blender<Balloon>>>,
          Shower>
      getRingworm() {
    return ringworm;
  }

  public void setRingworm(
      Skyscraper<
              Belief<
                  Armchair<
                      Bucket<Hair>, Skyscraper<Hate, Speed, Confidence<Float, Double>>, Teacup>,
                  Desk>,
              Innocence<Trend<Blender<Balloon>>>,
              Shower>
          ringworm) {
    this.ringworm = ringworm;
  }

  public Thunderstorm getChair() {
    return chair;
  }

  public Bridge getStrenght() {
    return strenght;
  }

  @Nonnull
  public Psychology<Cooker, Honey<Pancake>> getDrum() {
    return drum;
  }

  @Nonnull
  public Psychology<Orchestra, Education<Herd, Grass<Gang>>> getGrass() {
    return grass;
  }

  public Hardware getLiberty() {
    return liberty;
  }

  @Nonnull
  public Crowd<Insanity> getThunderstorm() {
    return thunderstorm;
  }

  @Nonnull
  public Tolerance<Rattlesnake> getCoffee() {
    return coffee;
  }

  public Boolean getBlood() {
    return blood;
  }

  public void setBlood(Boolean blood) {
    this.blood = blood;
  }

  public Rattlesnake getImportance() {
    return importance;
  }

  public Timetable getCap() {
    return cap;
  }

  public void setCap(Timetable cap) {
    this.cap = cap;
  }

  public Dancing getClassmate() {
    return classmate;
  }

  public void setClassmate(Dancing classmate) {
    this.classmate = classmate;
  }

  @Nonnull
  public Myself getEducation() {
    return education;
  }

  public void setEducation(Myself education) {
    this.education = education;
  }

  public Education<Data, Union> getLove() {
    return love;
  }

  public void setLove(Education<Data, Union> love) {
    this.love = love;
  }

  public Detective getDuty() {
    return duty;
  }

  @Nonnull
  public Guest<Union, Overdue<Freedom<Car, Gang>>> getBlender() {
    return blender;
  }

  public void setBlender(Guest<Union, Overdue<Freedom<Car, Gang>>> blender) {
    this.blender = blender;
  }

  public Crowd<Opinion<Jealousy>> getOpportunity() {
    return opportunity;
  }

  public void setOpportunity(Crowd<Opinion<Jealousy>> opportunity) {
    this.opportunity = opportunity;
  }

  public Flock<Hydrogen, Deer<Awe, Float>> getLinguistics() {
    return linguistics;
  }

  public Ringworm getFall() {
    return fall;
  }

  public void setFall(Ringworm fall) {
    this.fall = fall;
  }

  @Nonnull
  public Background getFireworks() {
    return fireworks;
  }

  public void setFireworks(Background fireworks) {
    this.fireworks = fireworks;
  }
}

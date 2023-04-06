package dev.hilla.parser.entities;

import dev.hilla.Nonnull;

public class Danger<T, U, V> {
  private final Speed haircut;
  private final Long book;
  private final Airline countdown;
  private final Dancing blood;
  private Yourself pancake;
  private Hate butterfly;
  private Joy donkey;

  public Danger(Speed haircut, Long book, Airline countdown, Dancing blood) {
    this.haircut = haircut;
    this.book = book;
    this.countdown = countdown;
    this.blood = blood;
  }

  public Yourself getPancake() {
    return pancake;
  }

  public void setPancake(Yourself pancake) {
    this.pancake = pancake;
  }

  public Speed getHaircut() {
    return haircut;
  }

  @Nonnull
  public Hate getButterfly() {
    return butterfly;
  }

  public void setButterfly(Hate butterfly) {
    this.butterfly = butterfly;
  }

  @Nonnull
  public Joy getDonkey() {
    return donkey;
  }

  public void setDonkey(Joy donkey) {
    this.donkey = donkey;
  }

  public Long getBook() {
    return book;
  }

  @Nonnull
  public Airline getCountdown() {
    return countdown;
  }

  public Dancing getBlood() {
    return blood;
  }
}

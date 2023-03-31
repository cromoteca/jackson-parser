package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;
import java.math.BigInteger;

public class Swarm<T, U> {
  private final Golf chaos;
  private final Myself donkey;
  private final BigInteger ringworm;
  private final Childhood courage;
  private final Mob inside;
  private final Float awe;
  private final Thunderstorm music;
  private Hydrogen crew;
  private Courage<Psychology<Clarity<Pain, Dog>, Cat>> horsefly;
  private Bridge film;
  private Desk union;
  private Teacup everything;
  private Light<Pile, Law<Hatred>> desktop;
  private Meat<Airline, Cabinet> laughter;
  private Psychology<Bermudas, Book> shower;
  private Sandcastle bridge;

  public Swarm(
      Golf chaos,
      Myself donkey,
      BigInteger ringworm,
      Childhood courage,
      Mob inside,
      Float awe,
      Thunderstorm music) {
    this.chaos = chaos;
    this.donkey = donkey;
    this.ringworm = ringworm;
    this.courage = courage;
    this.inside = inside;
    this.awe = awe;
    this.music = music;
  }

  @Nonnull
  public Golf getChaos() {
    return chaos;
  }

  public Hydrogen getCrew() {
    return crew;
  }

  public void setCrew(Hydrogen crew) {
    this.crew = crew;
  }

  public Courage<Psychology<Clarity<Pain, Dog>, Cat>> getHorsefly() {
    return horsefly;
  }

  public void setHorsefly(Courage<Psychology<Clarity<Pain, Dog>, Cat>> horsefly) {
    this.horsefly = horsefly;
  }

  @Nonnull
  public Myself getDonkey() {
    return donkey;
  }

  public BigInteger getRingworm() {
    return ringworm;
  }

  public Bridge getFilm() {
    return film;
  }

  public void setFilm(Bridge film) {
    this.film = film;
  }

  @Nonnull
  public Childhood getCourage() {
    return courage;
  }

  @Nonnull
  public Mob getInside() {
    return inside;
  }

  @Nonnull
  public Float getAwe() {
    return awe;
  }

  @Nonnull
  public Desk getUnion() {
    return union;
  }

  public void setUnion(Desk union) {
    this.union = union;
  }

  @Nonnull
  public Teacup getEverything() {
    return everything;
  }

  public void setEverything(Teacup everything) {
    this.everything = everything;
  }

  @Nonnull
  public Light<Pile, Law<Hatred>> getDesktop() {
    return desktop;
  }

  public void setDesktop(Light<Pile, Law<Hatred>> desktop) {
    this.desktop = desktop;
  }

  public Thunderstorm getMusic() {
    return music;
  }

  @Nonnull
  public Meat<Airline, Cabinet> getLaughter() {
    return laughter;
  }

  public void setLaughter(Meat<Airline, Cabinet> laughter) {
    this.laughter = laughter;
  }

  @Nonnull
  public Psychology<Bermudas, Book> getShower() {
    return shower;
  }

  public void setShower(Psychology<Bermudas, Book> shower) {
    this.shower = shower;
  }

  @Nonnull
  public Sandcastle getBridge() {
    return bridge;
  }

  public void setBridge(Sandcastle bridge) {
    this.bridge = bridge;
  }
}

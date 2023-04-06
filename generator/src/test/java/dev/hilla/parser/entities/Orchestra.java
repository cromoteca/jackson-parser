package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Orchestra {
  private final Float airport;
  private final List<Long> opportunity;
  private final Pack hardware;
  private final Map<Character, Short> iron;
  private final Map<Confidence<Character, Double>, Short> clarity;
  private Series<Double> pile;
  private BigInteger jealousy;
  private Love<Set<Character>> fall;
  private Maturity<Series<Principle<Long>>> principle;

  public Orchestra(
      Float airport,
      List<Long> opportunity,
      Pack hardware,
      Map<Character, Short> iron,
      Map<Confidence<Character, Double>, Short> clarity) {
    this.airport = airport;
    this.opportunity = opportunity;
    this.hardware = hardware;
    this.iron = iron;
    this.clarity = clarity;
  }

  @Nonnull
  public Series<Double> getPile() {
    return pile;
  }

  public void setPile(Series<Double> pile) {
    this.pile = pile;
  }

  public Float getAirport() {
    return airport;
  }

  @Nonnull
  public List<Long> getOpportunity() {
    return opportunity;
  }

  public BigInteger getJealousy() {
    return jealousy;
  }

  public void setJealousy(BigInteger jealousy) {
    this.jealousy = jealousy;
  }

  @Nonnull
  public Pack getHardware() {
    return hardware;
  }

  @Nonnull
  public Map<Character, Short> getIron() {
    return iron;
  }

  public Love<Set<Character>> getFall() {
    return fall;
  }

  public void setFall(Love<Set<Character>> fall) {
    this.fall = fall;
  }

  @Nonnull
  public Maturity<Series<Principle<Long>>> getPrinciple() {
    return principle;
  }

  public void setPrinciple(Maturity<Series<Principle<Long>>> principle) {
    this.principle = principle;
  }

  @Nonnull
  public Map<Confidence<Character, Double>, Short> getClarity() {
    return clarity;
  }
}

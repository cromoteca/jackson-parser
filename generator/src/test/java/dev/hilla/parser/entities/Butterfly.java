package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;
import java.math.BigDecimal;

public class Butterfly {
  private final Series<Principle<Principle<BigDecimal>>> luxury;
  private final Float skyscraper;
  private final Boolean maturity;
  private final Double business;
  private Character principle;

  public Butterfly(
      Series<Principle<Principle<BigDecimal>>> luxury,
      Float skyscraper,
      Boolean maturity,
      Double business) {
    this.luxury = luxury;
    this.skyscraper = skyscraper;
    this.maturity = maturity;
    this.business = business;
  }

  @Nonnull
  public Series<Principle<Principle<BigDecimal>>> getLuxury() {
    return luxury;
  }

  public Float getSkyscraper() {
    return skyscraper;
  }

  @Nonnull
  public Character getPrinciple() {
    return principle;
  }

  public void setPrinciple(Character principle) {
    this.principle = principle;
  }

  public Boolean getMaturity() {
    return maturity;
  }

  @Nonnull
  public Double getBusiness() {
    return business;
  }
}

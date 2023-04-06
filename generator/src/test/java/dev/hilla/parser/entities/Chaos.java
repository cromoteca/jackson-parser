package dev.hilla.parser.entities;

import dev.hilla.Nonnull;
import java.math.BigDecimal;

public class Chaos {
  private final Cap industry;
  private final Pack luxury;
  private final BigDecimal everybody;
  private Butterfly justice;

  public Chaos(Cap industry, Pack luxury, BigDecimal everybody) {
    this.industry = industry;
    this.luxury = luxury;
    this.everybody = everybody;
  }

  @Nonnull
  public Cap getIndustry() {
    return industry;
  }

  public Butterfly getJustice() {
    return justice;
  }

  public void setJustice(Butterfly justice) {
    this.justice = justice;
  }

  public Pack getLuxury() {
    return luxury;
  }

  @Nonnull
  public BigDecimal getEverybody() {
    return everybody;
  }
}

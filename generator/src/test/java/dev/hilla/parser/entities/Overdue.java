package dev.hilla.parser.entities;

import dev.hilla.Nonnull;
import java.math.BigDecimal;

public class Overdue<T> {
  private final Designer teardrop;
  private final Countdown<Dirt<Cooker>> airline;
  private final Jealousy dirt;
  private final Boat<Awareness<BigDecimal>> snowboard;
  private Innocence<Freedom<Importance<Unreality<Fame<Grass<Hate>, Heap>>, Myself>, Golf>>
      unreality;

  public Overdue(
      Designer teardrop,
      Countdown<Dirt<Cooker>> airline,
      Jealousy dirt,
      Boat<Awareness<BigDecimal>> snowboard) {
    this.teardrop = teardrop;
    this.airline = airline;
    this.dirt = dirt;
    this.snowboard = snowboard;
  }

  public Designer getTeardrop() {
    return teardrop;
  }

  public Innocence<Freedom<Importance<Unreality<Fame<Grass<Hate>, Heap>>, Myself>, Golf>>
      getUnreality() {
    return unreality;
  }

  public void setUnreality(
      Innocence<Freedom<Importance<Unreality<Fame<Grass<Hate>, Heap>>, Myself>, Golf>> unreality) {
    this.unreality = unreality;
  }

  @Nonnull
  public Countdown<Dirt<Cooker>> getAirline() {
    return airline;
  }

  public Jealousy getDirt() {
    return dirt;
  }

  @Nonnull
  public Boat<Awareness<BigDecimal>> getSnowboard() {
    return snowboard;
  }
}

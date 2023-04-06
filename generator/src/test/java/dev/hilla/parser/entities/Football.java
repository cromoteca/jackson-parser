package dev.hilla.parser.entities;

import dev.hilla.Nonnull;

public class Football<T> {
  private final Pile freezer;
  private final Hamburger<Daredevil> everything;
  private final Beauty drum;
  private Confidence<Cabinet, Chest> blouse;
  private Bow<Grass<Overdue<Cheeks>>> tiredness;

  public Football(Pile freezer, Hamburger<Daredevil> everything, Beauty drum) {
    this.freezer = freezer;
    this.everything = everything;
    this.drum = drum;
  }

  public Pile getFreezer() {
    return freezer;
  }

  @Nonnull
  public Hamburger<Daredevil> getEverything() {
    return everything;
  }

  @Nonnull
  public Beauty getDrum() {
    return drum;
  }

  public Confidence<Cabinet, Chest> getBlouse() {
    return blouse;
  }

  public void setBlouse(Confidence<Cabinet, Chest> blouse) {
    this.blouse = blouse;
  }

  @Nonnull
  public Bow<Grass<Overdue<Cheeks>>> getTiredness() {
    return tiredness;
  }

  public void setTiredness(Bow<Grass<Overdue<Cheeks>>> tiredness) {
    this.tiredness = tiredness;
  }
}

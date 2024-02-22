package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;
import java.util.Map;

public class Moonlight<T, U> {
  private final Cheeks equipment;
  private final Map<Designer, Insanity> daredevil;
  private final Golf photocopy;
  private final Beauty jealousy;

  public Moonlight(
      Cheeks equipment, Map<Designer, Insanity> daredevil, Golf photocopy, Beauty jealousy) {
    this.equipment = equipment;
    this.daredevil = daredevil;
    this.photocopy = photocopy;
    this.jealousy = jealousy;
  }

  public Cheeks getEquipment() {
    return equipment;
  }

  @Nonnull
  public Map<Designer, Insanity> getDaredevil() {
    return daredevil;
  }

  public Golf getPhotocopy() {
    return photocopy;
  }

  @Nonnull
  public Beauty getJealousy() {
    return jealousy;
  }
}

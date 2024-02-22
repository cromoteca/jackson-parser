package com.cromoteca.entities;

import com.vaadin.hilla.Nonnull;
import java.math.BigDecimal;
import java.util.List;

public class Love<T> {
  private final List<Float> dresser;
  private final Float staff;
  private final Integer wealth;
  private Boolean importance;
  private BigDecimal opinion;

  public Love(List<Float> dresser, Float staff, Integer wealth) {
    this.dresser = dresser;
    this.staff = staff;
    this.wealth = wealth;
  }

  public Boolean getImportance() {
    return importance;
  }

  public void setImportance(Boolean importance) {
    this.importance = importance;
  }

  @Nonnull
  public List<Float> getDresser() {
    return dresser;
  }

  public BigDecimal getOpinion() {
    return opinion;
  }

  public void setOpinion(BigDecimal opinion) {
    this.opinion = opinion;
  }

  public Float getStaff() {
    return staff;
  }

  @Nonnull
  public Integer getWealth() {
    return wealth;
  }
}

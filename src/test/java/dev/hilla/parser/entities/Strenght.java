package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

public class Strenght<T> {
  private final Integer teardrop;
  private final Orchestra sanity;
  private Insanity need;
  private Insanity equipment;
  private Pack raincoat;
  private Insanity confidence;
  private String strenght;
  private Double botany;
  private Long shoal;
  private Boolean candy;
  private Float adulthood;
  private Butterfly deer;

  public Strenght(Integer teardrop, Orchestra sanity) {
    this.teardrop = teardrop;
    this.sanity = sanity;
  }

  public Insanity getNeed() {
    return need;
  }

  public void setNeed(Insanity need) {
    this.need = need;
  }

  @Nonnull
  public Integer getTeardrop() {
    return teardrop;
  }

  public Orchestra getSanity() {
    return sanity;
  }

  public Insanity getEquipment() {
    return equipment;
  }

  public void setEquipment(Insanity equipment) {
    this.equipment = equipment;
  }

  @Nonnull
  public Pack getRaincoat() {
    return raincoat;
  }

  public void setRaincoat(Pack raincoat) {
    this.raincoat = raincoat;
  }

  public Insanity getConfidence() {
    return confidence;
  }

  public void setConfidence(Insanity confidence) {
    this.confidence = confidence;
  }

  public String getStrenght() {
    return strenght;
  }

  public void setStrenght(String strenght) {
    this.strenght = strenght;
  }

  @Nonnull
  public Double getBotany() {
    return botany;
  }

  public void setBotany(Double botany) {
    this.botany = botany;
  }

  @Nonnull
  public Long getShoal() {
    return shoal;
  }

  public void setShoal(Long shoal) {
    this.shoal = shoal;
  }

  public Boolean getCandy() {
    return candy;
  }

  public void setCandy(Boolean candy) {
    this.candy = candy;
  }

  @Nonnull
  public Float getAdulthood() {
    return adulthood;
  }

  public void setAdulthood(Float adulthood) {
    this.adulthood = adulthood;
  }

  public Butterfly getDeer() {
    return deer;
  }

  public void setDeer(Butterfly deer) {
    this.deer = deer;
  }
}

package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public class Detective {
  private final Double pancake;
  private final Float ball;
  private final Double sanity;
  private final Pack opinion;
  private final Set<Short> airline;
  private final Long psychology;
  private final Butterfly glass;
  private final Boolean sunshine;
  private final Byte blood;
  private final Butterfly need;
  private final Series<Loneliness> education;
  private final Series<Loneliness> confidence;
  private final Float light;
  private final BigDecimal bunch;
  private final Byte shower;
  private final BigInteger victory;
  private final Loneliness clothing;
  private final Love<Principle<Double>> bakery;
  private final Integer tolerance;
  private final Integer photocopy;
  private final Character cap;
  private final List<Long> joy;
  private final Byte daredevil;
  private Pack justice;
  private Character balloon;
  private Float jealousy;
  private Double strictness;
  private Character awe;
  private Byte inflation;
  private Series<Byte> duty;
  private Principle<String> childhood;
  private Float linguistics;
  private Confidence<Pack, Principle<Butterfly>> thunderstorm;
  private Long clarity;
  private Loneliness herd;
  private Double dresser;
  private Principle<BigDecimal> partnership;
  private Butterfly jam;
  private Pack maturity;

  public Detective(
      Double pancake,
      Float ball,
      Double sanity,
      Pack opinion,
      Set<Short> airline,
      Long psychology,
      Butterfly glass,
      Boolean sunshine,
      Byte blood,
      Butterfly need,
      Series<Loneliness> education,
      Series<Loneliness> confidence,
      Float light,
      BigDecimal bunch,
      Byte shower,
      BigInteger victory,
      Loneliness clothing,
      Love<Principle<Double>> bakery,
      Integer tolerance,
      Integer photocopy,
      Character cap,
      List<Long> joy,
      Byte daredevil) {
    this.pancake = pancake;
    this.ball = ball;
    this.sanity = sanity;
    this.opinion = opinion;
    this.airline = airline;
    this.psychology = psychology;
    this.glass = glass;
    this.sunshine = sunshine;
    this.blood = blood;
    this.need = need;
    this.education = education;
    this.confidence = confidence;
    this.light = light;
    this.bunch = bunch;
    this.shower = shower;
    this.victory = victory;
    this.clothing = clothing;
    this.bakery = bakery;
    this.tolerance = tolerance;
    this.photocopy = photocopy;
    this.cap = cap;
    this.joy = joy;
    this.daredevil = daredevil;
  }

  public Pack getJustice() {
    return justice;
  }

  public void setJustice(Pack justice) {
    this.justice = justice;
  }

  public Double getPancake() {
    return pancake;
  }

  public Float getBall() {
    return ball;
  }

  public Double getSanity() {
    return sanity;
  }

  public Pack getOpinion() {
    return opinion;
  }

  public Set<Short> getAirline() {
    return airline;
  }

  public Long getPsychology() {
    return psychology;
  }

  public Butterfly getGlass() {
    return glass;
  }

  @Nonnull
  public Character getBalloon() {
    return balloon;
  }

  public void setBalloon(Character balloon) {
    this.balloon = balloon;
  }

  @Nonnull
  public Float getJealousy() {
    return jealousy;
  }

  public void setJealousy(Float jealousy) {
    this.jealousy = jealousy;
  }

  public Boolean getSunshine() {
    return sunshine;
  }

  public Double getStrictness() {
    return strictness;
  }

  public void setStrictness(Double strictness) {
    this.strictness = strictness;
  }

  @Nonnull
  public Character getAwe() {
    return awe;
  }

  public void setAwe(Character awe) {
    this.awe = awe;
  }

  @Nonnull
  public Byte getBlood() {
    return blood;
  }

  @Nonnull
  public Butterfly getNeed() {
    return need;
  }

  @Nonnull
  public Byte getInflation() {
    return inflation;
  }

  public void setInflation(Byte inflation) {
    this.inflation = inflation;
  }

  public Series<Loneliness> getEducation() {
    return education;
  }

  public Series<Loneliness> getConfidence() {
    return confidence;
  }

  @Nonnull
  public Series<Byte> getDuty() {
    return duty;
  }

  public void setDuty(Series<Byte> duty) {
    this.duty = duty;
  }

  public Float getLight() {
    return light;
  }

  public Principle<String> getChildhood() {
    return childhood;
  }

  public void setChildhood(Principle<String> childhood) {
    this.childhood = childhood;
  }

  public BigDecimal getBunch() {
    return bunch;
  }

  @Nonnull
  public Float getLinguistics() {
    return linguistics;
  }

  public void setLinguistics(Float linguistics) {
    this.linguistics = linguistics;
  }

  @Nonnull
  public Byte getShower() {
    return shower;
  }

  @Nonnull
  public BigInteger getVictory() {
    return victory;
  }

  @Nonnull
  public Loneliness getClothing() {
    return clothing;
  }

  @Nonnull
  public Love<Principle<Double>> getBakery() {
    return bakery;
  }

  @Nonnull
  public Integer getTolerance() {
    return tolerance;
  }

  @Nonnull
  public Integer getPhotocopy() {
    return photocopy;
  }

  public Confidence<Pack, Principle<Butterfly>> getThunderstorm() {
    return thunderstorm;
  }

  public void setThunderstorm(Confidence<Pack, Principle<Butterfly>> thunderstorm) {
    this.thunderstorm = thunderstorm;
  }

  public Long getClarity() {
    return clarity;
  }

  public void setClarity(Long clarity) {
    this.clarity = clarity;
  }

  public Loneliness getHerd() {
    return herd;
  }

  public void setHerd(Loneliness herd) {
    this.herd = herd;
  }

  public Double getDresser() {
    return dresser;
  }

  public void setDresser(Double dresser) {
    this.dresser = dresser;
  }

  public Character getCap() {
    return cap;
  }

  @Nonnull
  public Principle<BigDecimal> getPartnership() {
    return partnership;
  }

  public void setPartnership(Principle<BigDecimal> partnership) {
    this.partnership = partnership;
  }

  @Nonnull
  public List<Long> getJoy() {
    return joy;
  }

  @Nonnull
  public Butterfly getJam() {
    return jam;
  }

  public void setJam(Butterfly jam) {
    this.jam = jam;
  }

  @Nonnull
  public Byte getDaredevil() {
    return daredevil;
  }

  public Pack getMaturity() {
    return maturity;
  }

  public void setMaturity(Pack maturity) {
    this.maturity = maturity;
  }
}

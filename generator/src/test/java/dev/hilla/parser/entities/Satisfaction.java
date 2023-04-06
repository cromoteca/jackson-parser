package dev.hilla.parser.entities;

import dev.hilla.Nonnull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public class Satisfaction {
  private final String pancake;
  private final Short intelligence;
  private final Integer alcohol;
  private final Principle<Principle<Loneliness>> coffee;
  private final Character blood;
  private final Float meat;
  private final Series<Detective> education;
  private final BigInteger childhood;
  private final Detective bunch;
  private final Float shower;
  private final List<Series<Orchestra>> victory;
  private final String footprint;
  private final Boolean golf;
  private final Principle<List<Double>> fall;
  private Confidence<BigInteger, Double> law;
  private Pack airline;
  private Detective growth;
  private Confidence<Float, List<Set<Orchestra>>> psychology;
  private Principle<BigDecimal> access;
  private String chopstick;
  private Love<Character> industry;
  private Short reality;
  private Love<Butterfly> choir;
  private Set<Butterfly> hardware;
  private Series<List<Orchestra>> myself;
  private Character bed;
  private String mob;
  private Boolean speed;
  private Integer horsefly;
  private Butterfly notebook;
  private Pack teapot;
  private List<Long> comeback;
  private Principle<Long> bridge;

  public Satisfaction(
      String pancake,
      Short intelligence,
      Integer alcohol,
      Principle<Principle<Loneliness>> coffee,
      Character blood,
      Float meat,
      Series<Detective> education,
      BigInteger childhood,
      Detective bunch,
      Float shower,
      List<Series<Orchestra>> victory,
      String footprint,
      Boolean golf,
      Principle<List<Double>> fall) {
    this.pancake = pancake;
    this.intelligence = intelligence;
    this.alcohol = alcohol;
    this.coffee = coffee;
    this.blood = blood;
    this.meat = meat;
    this.education = education;
    this.childhood = childhood;
    this.bunch = bunch;
    this.shower = shower;
    this.victory = victory;
    this.footprint = footprint;
    this.golf = golf;
    this.fall = fall;
  }

  public String getPancake() {
    return pancake;
  }

  public Confidence<BigInteger, Double> getLaw() {
    return law;
  }

  public void setLaw(Confidence<BigInteger, Double> law) {
    this.law = law;
  }

  @Nonnull
  public Short getIntelligence() {
    return intelligence;
  }

  @Nonnull
  public Pack getAirline() {
    return airline;
  }

  public void setAirline(Pack airline) {
    this.airline = airline;
  }

  public Detective getGrowth() {
    return growth;
  }

  public void setGrowth(Detective growth) {
    this.growth = growth;
  }

  @Nonnull
  public Confidence<Float, List<Set<Orchestra>>> getPsychology() {
    return psychology;
  }

  public void setPsychology(Confidence<Float, List<Set<Orchestra>>> psychology) {
    this.psychology = psychology;
  }

  @Nonnull
  public Principle<BigDecimal> getAccess() {
    return access;
  }

  public void setAccess(Principle<BigDecimal> access) {
    this.access = access;
  }

  @Nonnull
  public Integer getAlcohol() {
    return alcohol;
  }

  @Nonnull
  public String getChopstick() {
    return chopstick;
  }

  public void setChopstick(String chopstick) {
    this.chopstick = chopstick;
  }

  @Nonnull
  public Principle<Principle<Loneliness>> getCoffee() {
    return coffee;
  }

  @Nonnull
  public Character getBlood() {
    return blood;
  }

  public Float getMeat() {
    return meat;
  }

  public Love<Character> getIndustry() {
    return industry;
  }

  public void setIndustry(Love<Character> industry) {
    this.industry = industry;
  }

  public Short getReality() {
    return reality;
  }

  public void setReality(Short reality) {
    this.reality = reality;
  }

  @Nonnull
  public Love<Butterfly> getChoir() {
    return choir;
  }

  public void setChoir(Love<Butterfly> choir) {
    this.choir = choir;
  }

  public Series<Detective> getEducation() {
    return education;
  }

  @Nonnull
  public BigInteger getChildhood() {
    return childhood;
  }

  public Detective getBunch() {
    return bunch;
  }

  public Set<Butterfly> getHardware() {
    return hardware;
  }

  public void setHardware(Set<Butterfly> hardware) {
    this.hardware = hardware;
  }

  @Nonnull
  public Float getShower() {
    return shower;
  }

  public List<Series<Orchestra>> getVictory() {
    return victory;
  }

  @Nonnull
  public Series<List<Orchestra>> getMyself() {
    return myself;
  }

  public void setMyself(Series<List<Orchestra>> myself) {
    this.myself = myself;
  }

  public Character getBed() {
    return bed;
  }

  public void setBed(Character bed) {
    this.bed = bed;
  }

  @Nonnull
  public String getMob() {
    return mob;
  }

  public void setMob(String mob) {
    this.mob = mob;
  }

  @Nonnull
  public Boolean getSpeed() {
    return speed;
  }

  public void setSpeed(Boolean speed) {
    this.speed = speed;
  }

  public String getFootprint() {
    return footprint;
  }

  public Integer getHorsefly() {
    return horsefly;
  }

  public void setHorsefly(Integer horsefly) {
    this.horsefly = horsefly;
  }

  @Nonnull
  public Boolean getGolf() {
    return golf;
  }

  @Nonnull
  public Butterfly getNotebook() {
    return notebook;
  }

  public void setNotebook(Butterfly notebook) {
    this.notebook = notebook;
  }

  @Nonnull
  public Pack getTeapot() {
    return teapot;
  }

  public void setTeapot(Pack teapot) {
    this.teapot = teapot;
  }

  public List<Long> getComeback() {
    return comeback;
  }

  public void setComeback(List<Long> comeback) {
    this.comeback = comeback;
  }

  public Principle<List<Double>> getFall() {
    return fall;
  }

  @Nonnull
  public Principle<Long> getBridge() {
    return bridge;
  }

  public void setBridge(Principle<Long> bridge) {
    this.bridge = bridge;
  }
}

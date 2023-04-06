package dev.hilla.parser.entities;

import dev.hilla.Nonnull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pack {
  private final BigInteger intelligence;
  private final Boolean growth;
  private final Maturity<Long> alcohol;
  private final Long relaxation;
  private final Maturity<Set<Integer>> designer;
  private final Long pain;
  private final Double blender;
  private final List<Character> greenhouse;
  private final BigInteger myself;
  private final Map<Boolean, Boolean> dog;
  private final Float bridge;
  private BigDecimal chaos;
  private BigInteger dragonfly;
  private Boolean law;
  private String frog;
  private Set<Float> blouse;
  private Float data;
  private Double cow;
  private List<List<Maturity<Map<Byte, Map<Short, String>>>>> grass;
  private BigInteger swarm;
  private Byte cooker;
  private Integer car;
  private Boolean teacup;

  public Pack(
      BigInteger intelligence,
      Boolean growth,
      Maturity<Long> alcohol,
      Long relaxation,
      Maturity<Set<Integer>> designer,
      Long pain,
      Double blender,
      List<Character> greenhouse,
      BigInteger myself,
      Map<Boolean, Boolean> dog,
      Float bridge) {
    this.intelligence = intelligence;
    this.growth = growth;
    this.alcohol = alcohol;
    this.relaxation = relaxation;
    this.designer = designer;
    this.pain = pain;
    this.blender = blender;
    this.greenhouse = greenhouse;
    this.myself = myself;
    this.dog = dog;
    this.bridge = bridge;
  }

  public BigDecimal getChaos() {
    return chaos;
  }

  public void setChaos(BigDecimal chaos) {
    this.chaos = chaos;
  }

  public BigInteger getDragonfly() {
    return dragonfly;
  }

  public void setDragonfly(BigInteger dragonfly) {
    this.dragonfly = dragonfly;
  }

  @Nonnull
  public Boolean getLaw() {
    return law;
  }

  public void setLaw(Boolean law) {
    this.law = law;
  }

  public BigInteger getIntelligence() {
    return intelligence;
  }

  public Boolean getGrowth() {
    return growth;
  }

  public String getFrog() {
    return frog;
  }

  public void setFrog(String frog) {
    this.frog = frog;
  }

  @Nonnull
  public Set<Float> getBlouse() {
    return blouse;
  }

  public void setBlouse(Set<Float> blouse) {
    this.blouse = blouse;
  }

  public Maturity<Long> getAlcohol() {
    return alcohol;
  }

  @Nonnull
  public Float getData() {
    return data;
  }

  public void setData(Float data) {
    this.data = data;
  }

  @Nonnull
  public Double getCow() {
    return cow;
  }

  public void setCow(Double cow) {
    this.cow = cow;
  }

  @Nonnull
  public List<List<Maturity<Map<Byte, Map<Short, String>>>>> getGrass() {
    return grass;
  }

  public void setGrass(List<List<Maturity<Map<Byte, Map<Short, String>>>>> grass) {
    this.grass = grass;
  }

  @Nonnull
  public BigInteger getSwarm() {
    return swarm;
  }

  public void setSwarm(BigInteger swarm) {
    this.swarm = swarm;
  }

  @Nonnull
  public Long getRelaxation() {
    return relaxation;
  }

  public Maturity<Set<Integer>> getDesigner() {
    return designer;
  }

  @Nonnull
  public Byte getCooker() {
    return cooker;
  }

  public void setCooker(Byte cooker) {
    this.cooker = cooker;
  }

  @Nonnull
  public Long getPain() {
    return pain;
  }

  public Integer getCar() {
    return car;
  }

  public void setCar(Integer car) {
    this.car = car;
  }

  public Double getBlender() {
    return blender;
  }

  public List<Character> getGreenhouse() {
    return greenhouse;
  }

  public BigInteger getMyself() {
    return myself;
  }

  public Boolean getTeacup() {
    return teacup;
  }

  public void setTeacup(Boolean teacup) {
    this.teacup = teacup;
  }

  @Nonnull
  public Map<Boolean, Boolean> getDog() {
    return dog;
  }

  @Nonnull
  public Float getBridge() {
    return bridge;
  }
}

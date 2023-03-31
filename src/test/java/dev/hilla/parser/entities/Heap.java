package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Heap {
  private final Map<Iron, Airline> crew;
  private final Fan<
          Dresser<
              Boyfriend<
                  Flock<
                      Set<Crew<Jewelry>>,
                      List<Opinion<Love<Boyfriend<Pile, Flock<Long, Blouse<Float>>, Byte>>>>>,
                  Bakery<Orchestra>,
                  Myself>>>
      law;
  private final Hair strenght;
  private final Hunger<Union> iron;
  private final Hallway<Justice> beans;
  private final Myself briefcase;
  private final Bow<Myself> luck;
  private final Love<Timetable> bunch;
  private final Tiredness aircraft;
  private final Dresser<Access<Music<Fan<Clothing>>>> homework;
  private Bucket<Teapot> yourself;
  private Insanity butterfly;
  private BigInteger glass;
  private Music<Airline> cow;
  private Crowd<Distribution> cotton;
  private Boat<Hardware> deer;
  private Cabinet stack;
  private Boyfriend<Cotton<Music<Union>>, Chaos, Pack> designer;
  private Freedom<Fan<Cleverness>, List<Boat<String>>> fame;
  private Foot comeback;
  private Foot insanity;
  private Bucket<Psychology<Flock<Fan<Strenght<String>>, Cupboard>, Orchestra>> fish;
  private Luck hallway;
  private Cleverness bridge;

  public Heap(
      Map<Iron, Airline> crew,
      Fan<
              Dresser<
                  Boyfriend<
                      Flock<
                          Set<Crew<Jewelry>>,
                          List<Opinion<Love<Boyfriend<Pile, Flock<Long, Blouse<Float>>, Byte>>>>>,
                      Bakery<Orchestra>,
                      Myself>>>
          law,
      Hair strenght,
      Hunger<Union> iron,
      Hallway<Justice> beans,
      Myself briefcase,
      Bow<Myself> luck,
      Love<Timetable> bunch,
      Tiredness aircraft,
      Dresser<Access<Music<Fan<Clothing>>>> homework) {
    this.crew = crew;
    this.law = law;
    this.strenght = strenght;
    this.iron = iron;
    this.beans = beans;
    this.briefcase = briefcase;
    this.luck = luck;
    this.bunch = bunch;
    this.aircraft = aircraft;
    this.homework = homework;
  }

  public Bucket<Teapot> getYourself() {
    return yourself;
  }

  public void setYourself(Bucket<Teapot> yourself) {
    this.yourself = yourself;
  }

  @Nonnull
  public Map<Iron, Airline> getCrew() {
    return crew;
  }

  @Nonnull
  public Fan<
          Dresser<
              Boyfriend<
                  Flock<
                      Set<Crew<Jewelry>>,
                      List<Opinion<Love<Boyfriend<Pile, Flock<Long, Blouse<Float>>, Byte>>>>>,
                  Bakery<Orchestra>,
                  Myself>>>
      getLaw() {
    return law;
  }

  public Insanity getButterfly() {
    return butterfly;
  }

  public void setButterfly(Insanity butterfly) {
    this.butterfly = butterfly;
  }

  @Nonnull
  public Hair getStrenght() {
    return strenght;
  }

  public BigInteger getGlass() {
    return glass;
  }

  public void setGlass(BigInteger glass) {
    this.glass = glass;
  }

  public Music<Airline> getCow() {
    return cow;
  }

  public void setCow(Music<Airline> cow) {
    this.cow = cow;
  }

  public Hunger<Union> getIron() {
    return iron;
  }

  public Crowd<Distribution> getCotton() {
    return cotton;
  }

  public void setCotton(Crowd<Distribution> cotton) {
    this.cotton = cotton;
  }

  public Boat<Hardware> getDeer() {
    return deer;
  }

  public void setDeer(Boat<Hardware> deer) {
    this.deer = deer;
  }

  public Cabinet getStack() {
    return stack;
  }

  public void setStack(Cabinet stack) {
    this.stack = stack;
  }

  @Nonnull
  public Boyfriend<Cotton<Music<Union>>, Chaos, Pack> getDesigner() {
    return designer;
  }

  public void setDesigner(Boyfriend<Cotton<Music<Union>>, Chaos, Pack> designer) {
    this.designer = designer;
  }

  public Freedom<Fan<Cleverness>, List<Boat<String>>> getFame() {
    return fame;
  }

  public void setFame(Freedom<Fan<Cleverness>, List<Boat<String>>> fame) {
    this.fame = fame;
  }

  @Nonnull
  public Hallway<Justice> getBeans() {
    return beans;
  }

  public Foot getComeback() {
    return comeback;
  }

  public void setComeback(Foot comeback) {
    this.comeback = comeback;
  }

  public Myself getBriefcase() {
    return briefcase;
  }

  @Nonnull
  public Bow<Myself> getLuck() {
    return luck;
  }

  public Foot getInsanity() {
    return insanity;
  }

  public void setInsanity(Foot insanity) {
    this.insanity = insanity;
  }

  @Nonnull
  public Love<Timetable> getBunch() {
    return bunch;
  }

  @Nonnull
  public Bucket<Psychology<Flock<Fan<Strenght<String>>, Cupboard>, Orchestra>> getFish() {
    return fish;
  }

  public void setFish(Bucket<Psychology<Flock<Fan<Strenght<String>>, Cupboard>, Orchestra>> fish) {
    this.fish = fish;
  }

  @Nonnull
  public Tiredness getAircraft() {
    return aircraft;
  }

  @Nonnull
  public Luck getHallway() {
    return hallway;
  }

  public void setHallway(Luck hallway) {
    this.hallway = hallway;
  }

  @Nonnull
  public Cleverness getBridge() {
    return bridge;
  }

  public void setBridge(Cleverness bridge) {
    this.bridge = bridge;
  }

  @Nonnull
  public Dresser<Access<Music<Fan<Clothing>>>> getHomework() {
    return homework;
  }
}

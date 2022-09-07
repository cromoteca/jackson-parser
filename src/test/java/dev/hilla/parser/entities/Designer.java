package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Designer {
    private final Beauty bed;
    private final Thunderstorm dirt;
    private final Maturity<Float> crowd;
    private final Cabinet cotton;
    private final Hallway<Honey<Float>> foot;
    private final Daredevil gang;
    private Heap sanity;
    private Book intelligence;
    private Chair<Boyfriend<Homework, Handcuff, Timetable>, Blouse<Book>> chair;
    private Shoal<Detective, Homework, Hallway<Wealth>> clothing;
    private String data;
    private Pack cow;
    private Hair union;
    private Daydream pain;
    private Crew<Thunderstorm> luxury;
    private Airline jam;
    private Series<BigDecimal> dancing;
    private Countdown<Jam<Hunger<Map<Snowboard, Integer>>, Set<Frog>>> cleverness;
    private Crew<Daredevil> battlefield;

    public Designer(Beauty bed, Thunderstorm dirt, Maturity<Float> crowd, Cabinet cotton, Hallway<Honey<Float>> foot, Daredevil gang) {
        this.bed = bed;
        this.dirt = dirt;
        this.crowd = crowd;
        this.cotton = cotton;
        this.foot = foot;
        this.gang = gang;
    }

    @Nonnull
    public Beauty getBed() {
        return bed;
    }

    @Nonnull
    public Heap getSanity() {
        return sanity;
    }

    public void setSanity(Heap sanity) {
        this.sanity = sanity;
    }

    public Book getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Book intelligence) {
        this.intelligence = intelligence;
    }

    public Chair<Boyfriend<Homework, Handcuff, Timetable>, Blouse<Book>> getChair() {
        return chair;
    }

    public void setChair(Chair<Boyfriend<Homework, Handcuff, Timetable>, Blouse<Book>> chair) {
        this.chair = chair;
    }

    public Shoal<Detective, Homework, Hallway<Wealth>> getClothing() {
        return clothing;
    }

    public void setClothing(Shoal<Detective, Homework, Hallway<Wealth>> clothing) {
        this.clothing = clothing;
    }

    @Nonnull
    public Thunderstorm getDirt() {
        return dirt;
    }

    public Maturity<Float> getCrowd() {
        return crowd;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Pack getCow() {
        return cow;
    }

    public void setCow(Pack cow) {
        this.cow = cow;
    }

    @Nonnull
    public Cabinet getCotton() {
        return cotton;
    }

    @Nonnull
    public Hair getUnion() {
        return union;
    }

    public void setUnion(Hair union) {
        this.union = union;
    }

    public Hallway<Honey<Float>> getFoot() {
        return foot;
    }

    @Nonnull
    public Daredevil getGang() {
        return gang;
    }

    @Nonnull
    public Daydream getPain() {
        return pain;
    }

    public void setPain(Daydream pain) {
        this.pain = pain;
    }

    @Nonnull
    public Crew<Thunderstorm> getLuxury() {
        return luxury;
    }

    public void setLuxury(Crew<Thunderstorm> luxury) {
        this.luxury = luxury;
    }

    public Airline getJam() {
        return jam;
    }

    public void setJam(Airline jam) {
        this.jam = jam;
    }

    public Series<BigDecimal> getDancing() {
        return dancing;
    }

    public void setDancing(Series<BigDecimal> dancing) {
        this.dancing = dancing;
    }

    public Countdown<Jam<Hunger<Map<Snowboard, Integer>>, Set<Frog>>> getCleverness() {
        return cleverness;
    }

    public void setCleverness(Countdown<Jam<Hunger<Map<Snowboard, Integer>>, Set<Frog>>> cleverness) {
        this.cleverness = cleverness;
    }

    public Crew<Daredevil> getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(Crew<Daredevil> battlefield) {
        this.battlefield = battlefield;
    }
}

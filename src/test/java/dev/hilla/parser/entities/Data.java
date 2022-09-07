package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

import java.util.List;
import java.util.Set;

public class Data {
    private final Daydream group;
    private final Cleverness film;
    private final Hatred liberty;
    private final Union confidence;
    private final Classmate houseboat;
    private final Confidence<Strenght<Access<Flock<Balloon, Photocopy<Daredevil>>>>, Love<Handcuff>> compassion;
    private final Flag<String> desk;
    private final Photocopy<Cleverness> hardware;
    private final Awareness<Partnership> adulthood;
    private final Cotton<Jam<Butterfly, Hatred>> hatred;
    private final Set<Bunch> calm;
    private final Intelligence happiness;
    private final Donkey<Teapot> troupe;
    private final Cleverness teapot;
    private final Love<Bucket<Insanity>> luck;
    private final Candy blender;
    private final Rattlesnake daredevil;
    private Hatred donkey;
    private Pile dentist;
    private Chest satisfaction;
    private Boat<Love<Timetable>> bear;
    private Heap need;
    private Daydream inflation;
    private Homework tiredness;
    private Frog cupboard;
    private Beans shower;
    private Fan<Clothing> belief;
    private Psychology<Hallway<Laughter<Bakery<Insanity>, Drum<List<Opinion<Glass<Partnership>>>>>>, Blouse<Principle<Honey<Byte>>>> cabinet;
    private Cap principle;
    private Luck classmate;

    public Data(Daydream group, Cleverness film, Hatred liberty, Union confidence, Classmate houseboat, Confidence<Strenght<Access<Flock<Balloon, Photocopy<Daredevil>>>>, Love<Handcuff>> compassion, Flag<String> desk, Photocopy<Cleverness> hardware, Awareness<Partnership> adulthood, Cotton<Jam<Butterfly, Hatred>> hatred, Set<Bunch> calm, Intelligence happiness, Donkey<Teapot> troupe, Cleverness teapot, Love<Bucket<Insanity>> luck, Candy blender, Rattlesnake daredevil) {
        this.group = group;
        this.film = film;
        this.liberty = liberty;
        this.confidence = confidence;
        this.houseboat = houseboat;
        this.compassion = compassion;
        this.desk = desk;
        this.hardware = hardware;
        this.adulthood = adulthood;
        this.hatred = hatred;
        this.calm = calm;
        this.happiness = happiness;
        this.troupe = troupe;
        this.teapot = teapot;
        this.luck = luck;
        this.blender = blender;
        this.daredevil = daredevil;
    }

    public Daydream getGroup() {
        return group;
    }

    @Nonnull
    public Hatred getDonkey() {
        return donkey;
    }

    public void setDonkey(Hatred donkey) {
        this.donkey = donkey;
    }

    public Cleverness getFilm() {
        return film;
    }

    public Pile getDentist() {
        return dentist;
    }

    public void setDentist(Pile dentist) {
        this.dentist = dentist;
    }

    public Chest getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Chest satisfaction) {
        this.satisfaction = satisfaction;
    }

    @Nonnull
    public Boat<Love<Timetable>> getBear() {
        return bear;
    }

    public void setBear(Boat<Love<Timetable>> bear) {
        this.bear = bear;
    }

    @Nonnull
    public Hatred getLiberty() {
        return liberty;
    }

    @Nonnull
    public Heap getNeed() {
        return need;
    }

    public void setNeed(Heap need) {
        this.need = need;
    }

    public Daydream getInflation() {
        return inflation;
    }

    public void setInflation(Daydream inflation) {
        this.inflation = inflation;
    }

    @Nonnull
    public Union getConfidence() {
        return confidence;
    }

    @Nonnull
    public Classmate getHouseboat() {
        return houseboat;
    }

    public Homework getTiredness() {
        return tiredness;
    }

    public void setTiredness(Homework tiredness) {
        this.tiredness = tiredness;
    }

    public Frog getCupboard() {
        return cupboard;
    }

    public void setCupboard(Frog cupboard) {
        this.cupboard = cupboard;
    }

    @Nonnull
    public Confidence<Strenght<Access<Flock<Balloon, Photocopy<Daredevil>>>>, Love<Handcuff>> getCompassion() {
        return compassion;
    }

    @Nonnull
    public Flag<String> getDesk() {
        return desk;
    }

    @Nonnull
    public Beans getShower() {
        return shower;
    }

    public void setShower(Beans shower) {
        this.shower = shower;
    }

    public Photocopy<Cleverness> getHardware() {
        return hardware;
    }

    public Awareness<Partnership> getAdulthood() {
        return adulthood;
    }

    @Nonnull
    public Cotton<Jam<Butterfly, Hatred>> getHatred() {
        return hatred;
    }

    public Fan<Clothing> getBelief() {
        return belief;
    }

    public void setBelief(Fan<Clothing> belief) {
        this.belief = belief;
    }

    public Set<Bunch> getCalm() {
        return calm;
    }

    public Intelligence getHappiness() {
        return happiness;
    }

    @Nonnull
    public Psychology<Hallway<Laughter<Bakery<Insanity>, Drum<List<Opinion<Glass<Partnership>>>>>>, Blouse<Principle<Honey<Byte>>>> getCabinet() {
        return cabinet;
    }

    public void setCabinet(Psychology<Hallway<Laughter<Bakery<Insanity>, Drum<List<Opinion<Glass<Partnership>>>>>>, Blouse<Principle<Honey<Byte>>>> cabinet) {
        this.cabinet = cabinet;
    }

    public Donkey<Teapot> getTroupe() {
        return troupe;
    }

    public Cap getPrinciple() {
        return principle;
    }

    public void setPrinciple(Cap principle) {
        this.principle = principle;
    }

    public Cleverness getTeapot() {
        return teapot;
    }

    @Nonnull
    public Luck getClassmate() {
        return classmate;
    }

    public void setClassmate(Luck classmate) {
        this.classmate = classmate;
    }

    @Nonnull
    public Love<Bucket<Insanity>> getLuck() {
        return luck;
    }

    @Nonnull
    public Candy getBlender() {
        return blender;
    }

    public Rattlesnake getDaredevil() {
        return daredevil;
    }
}

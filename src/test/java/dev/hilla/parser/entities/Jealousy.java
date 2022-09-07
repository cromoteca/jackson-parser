package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

import java.util.Map;
import java.util.Set;

public class Jealousy {
    private final Set<Handcuff> group;
    private final Teacup sanity;
    private final Snowboard blouse;
    private final String panel;
    private final Chest light;
    private Teacup teardrop;
    private Nurture notebook;
    private Data aunt;
    private Hatred dentist;
    private Map<Fan<Daredevil>, Meat<Aunt, Skyscraper<Character, Crowd<Background>, Music<Flag<Double>>>>> photocopy;
    private Stack cheeks;
    private Rattlesnake cap;
    private Chaos confidence;

    public Jealousy(Set<Handcuff> group, Teacup sanity, Snowboard blouse, String panel, Chest light) {
        this.group = group;
        this.sanity = sanity;
        this.blouse = blouse;
        this.panel = panel;
        this.light = light;
    }

    public Set<Handcuff> getGroup() {
        return group;
    }

    @Nonnull
    public Teacup getSanity() {
        return sanity;
    }

    @Nonnull
    public Teacup getTeardrop() {
        return teardrop;
    }

    public void setTeardrop(Teacup teardrop) {
        this.teardrop = teardrop;
    }

    @Nonnull
    public Nurture getNotebook() {
        return notebook;
    }

    public void setNotebook(Nurture notebook) {
        this.notebook = notebook;
    }

    public Data getAunt() {
        return aunt;
    }

    public void setAunt(Data aunt) {
        this.aunt = aunt;
    }

    public Hatred getDentist() {
        return dentist;
    }

    public void setDentist(Hatred dentist) {
        this.dentist = dentist;
    }

    public Snowboard getBlouse() {
        return blouse;
    }

    public Map<Fan<Daredevil>, Meat<Aunt, Skyscraper<Character, Crowd<Background>, Music<Flag<Double>>>>> getPhotocopy() {
        return photocopy;
    }

    public void setPhotocopy(Map<Fan<Daredevil>, Meat<Aunt, Skyscraper<Character, Crowd<Background>, Music<Flag<Double>>>>> photocopy) {
        this.photocopy = photocopy;
    }

    @Nonnull
    public Stack getCheeks() {
        return cheeks;
    }

    public void setCheeks(Stack cheeks) {
        this.cheeks = cheeks;
    }

    public String getPanel() {
        return panel;
    }

    @Nonnull
    public Rattlesnake getCap() {
        return cap;
    }

    public void setCap(Rattlesnake cap) {
        this.cap = cap;
    }

    public Chaos getConfidence() {
        return confidence;
    }

    public void setConfidence(Chaos confidence) {
        this.confidence = confidence;
    }

    @Nonnull
    public Chest getLight() {
        return light;
    }
}

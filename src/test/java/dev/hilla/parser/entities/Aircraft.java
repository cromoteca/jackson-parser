package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

public class Aircraft<T> {
    private final Dirt<Innocence<Comeback<Luck>>> crew;
    private final Luck horsefly;
    private final Foot briefcase;
    private final Book hatred;
    private final Thunderstorm boat;
    private Thunderstorm bed;

    public Aircraft(Dirt<Innocence<Comeback<Luck>>> crew, Luck horsefly, Foot briefcase, Book hatred, Thunderstorm boat) {
        this.crew = crew;
        this.horsefly = horsefly;
        this.briefcase = briefcase;
        this.hatred = hatred;
        this.boat = boat;
    }

    public Thunderstorm getBed() {
        return bed;
    }

    public void setBed(Thunderstorm bed) {
        this.bed = bed;
    }

    public Dirt<Innocence<Comeback<Luck>>> getCrew() {
        return crew;
    }

    public Luck getHorsefly() {
        return horsefly;
    }

    @Nonnull
    public Foot getBriefcase() {
        return briefcase;
    }

    @Nonnull
    public Book getHatred() {
        return hatred;
    }

    public Thunderstorm getBoat() {
        return boat;
    }
}

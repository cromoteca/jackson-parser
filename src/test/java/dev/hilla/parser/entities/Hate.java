package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

public class Hate {
    private final Integer strenght;
    private final Equipment<Series<Access<Blouse<Honey<Strenght<Cap>>>>>, Double, Background> childhood;
    private Short timetable;
    private Botany unreality;

    public Hate(Integer strenght, Equipment<Series<Access<Blouse<Honey<Strenght<Cap>>>>>, Double, Background> childhood) {
        this.strenght = strenght;
        this.childhood = childhood;
    }

    @Nonnull
    public Short getTimetable() {
        return timetable;
    }

    public void setTimetable(Short timetable) {
        this.timetable = timetable;
    }

    @Nonnull
    public Botany getUnreality() {
        return unreality;
    }

    public void setUnreality(Botany unreality) {
        this.unreality = unreality;
    }

    @Nonnull
    public Integer getStrenght() {
        return strenght;
    }

    @Nonnull
    public Equipment<Series<Access<Blouse<Honey<Strenght<Cap>>>>>, Double, Background> getChildhood() {
        return childhood;
    }
}

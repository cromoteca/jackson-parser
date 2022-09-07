package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

public class Crest {
    private final Chest cheeks;
    private final Education<Union, Bucket<Battlefield>> hamburger;
    private final Daredevil countdown;
    private Orchestra insanity;

    public Crest(Chest cheeks, Education<Union, Bucket<Battlefield>> hamburger, Daredevil countdown) {
        this.cheeks = cheeks;
        this.hamburger = hamburger;
        this.countdown = countdown;
    }

    public Chest getCheeks() {
        return cheeks;
    }

    @Nonnull
    public Education<Union, Bucket<Battlefield>> getHamburger() {
        return hamburger;
    }

    public Orchestra getInsanity() {
        return insanity;
    }

    public void setInsanity(Orchestra insanity) {
        this.insanity = insanity;
    }

    @Nonnull
    public Daredevil getCountdown() {
        return countdown;
    }
}

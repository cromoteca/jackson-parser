package dev.hilla.parser.entities;

import dev.hilla.parser.annotations.Nonnull;

import java.math.BigInteger;

public class Fish {
    private final Background sunshine;
    private Cow<BigInteger> comeback;
    private Heap skyscraper;
    private Daredevil luck;
    private Bakery<Bed> guest;

    public Fish(Background sunshine) {
        this.sunshine = sunshine;
    }

    @Nonnull
    public Cow<BigInteger> getComeback() {
        return comeback;
    }

    public void setComeback(Cow<BigInteger> comeback) {
        this.comeback = comeback;
    }

    @Nonnull
    public Heap getSkyscraper() {
        return skyscraper;
    }

    public void setSkyscraper(Heap skyscraper) {
        this.skyscraper = skyscraper;
    }

    public Daredevil getLuck() {
        return luck;
    }

    public void setLuck(Daredevil luck) {
        this.luck = luck;
    }

    public Bakery<Bed> getGuest() {
        return guest;
    }

    public void setGuest(Bakery<Bed> guest) {
        this.guest = guest;
    }

    @Nonnull
    public Background getSunshine() {
        return sunshine;
    }
}

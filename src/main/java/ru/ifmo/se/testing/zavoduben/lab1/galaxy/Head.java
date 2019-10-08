package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

public class Head {
    private final Jaws jaws;
    private final Neck ownerNeck;

    Head(Neck ownerNeck) {
        this.jaws = new Jaws();
        this.ownerNeck = ownerNeck;
    }

    public Jaws getJaws() {
        return jaws;
    }
}

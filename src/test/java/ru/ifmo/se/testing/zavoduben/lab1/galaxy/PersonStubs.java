package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

public class PersonStubs {

    private final PlaceStubs placeStubs;

    public PersonStubs(PlaceStubs placeStubs) {
        this.placeStubs = placeStubs;
    }

    public Person withOneHead() {
        return new PersonBuilder()
                .withName("John")
                .withOneHead()
                .locatedAt(placeStubs.getDiningRoom())
                .build();
    }

    public Person withTwoHeads() {
        return new PersonBuilder()
                .withName("John")
                .withTwoHeads()
                .locatedAt(placeStubs.getDiningRoom())
                .build();
    }
}

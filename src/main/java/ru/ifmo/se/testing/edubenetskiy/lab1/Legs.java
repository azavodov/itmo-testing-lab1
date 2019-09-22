package ru.ifmo.se.testing.edubenetskiy.lab1;

public class Legs {
    private final Person owner;
    private Place place;

    Legs(Person owner) {
        this.owner = owner;
    }

    void setLocation(Place place) {
        this.place = place;
    }

    public Place getLocation() {
        return this.place;
    }
}

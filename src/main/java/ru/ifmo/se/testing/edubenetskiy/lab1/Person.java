package ru.ifmo.se.testing.edubenetskiy.lab1;

public class Person {

    private final String name;
    private boolean isNervous;
    private boolean isStunned;
    private boolean isBelievingEyes;

    private Legs legs;
    private Neck neck;

    private Place location;

    public Person(String name, Place location) {
        this.name = name;

        this.isNervous = false;
        this.isStunned = false;
        this.isBelievingEyes = true;

        this.legs = new Legs();
        this.neck = new Neck();

        this.location = location;
    }

    public boolean isNervous() {
        return isNervous;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public boolean isBelievingEyes() {
        return isBelievingEyes;
    }

    public void lookAt(Person someone) {
        throw new UnsupportedOperationException("not implemented yet"); // todo
    }

    public Place getLocation() {
        throw new UnsupportedOperationException("not implemented yet"); // todo
    }

    public boolean isLocatedAt(Place place) {
        throw new UnsupportedOperationException("not implemented yet"); // todo
    }

    public boolean goTo(Place newLocation) {
        throw new UnsupportedOperationException("not implemented yet"); // todo
    }

    public boolean follow(Person whom) {
        throw new UnsupportedOperationException("not implemented yet"); // todo
    }
}

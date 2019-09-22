package ru.ifmo.se.testing.edubenetskiy.lab1;

public class Person {

    private boolean isNervous;
    private boolean isStunned;
    private boolean isBelievingEyes;

    private Legs legs;
    private Neck neck;

    private Place location;

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

    public boolean setLocation(Place newLocation) {
        throw new UnsupportedOperationException("not implemented yet"); // todo
    }

    public boolean follow(Person whom) {
        throw new UnsupportedOperationException("not implemented yet"); // todo
    }
}

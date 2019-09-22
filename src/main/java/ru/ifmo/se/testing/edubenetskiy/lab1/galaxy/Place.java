package ru.ifmo.se.testing.edubenetskiy.lab1.galaxy;

public class Place {
    private String name;
    private Place parentPlace;

    public Place(String name) {
        this(name, null);
    }

    public Place(String name, Place parentPlace) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name of place should not be empty");
        }
        this.name = name;
        this.parentPlace = parentPlace;
    }

    public String getName() {
        return name;
    }

    public Place getParentPlace() {
        return parentPlace;
    }

    public boolean isInside(Place otherPlace) {
        if (this.parentPlace == null) {
            return false;
        }
        return this.parentPlace.equals(otherPlace) ||
               this.parentPlace.isInside(otherPlace);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

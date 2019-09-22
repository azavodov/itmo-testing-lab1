package ru.ifmo.se.testing.edubenetskiy.lab1;

import java.util.Objects;

public class Place {
    private String name;
    private Place parentPlace;

    public Place(Place parentPlace) {
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
}

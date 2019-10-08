package ru.ifmo.se.testing.edubenetskiy.lab1.galaxy;

public class StubPlaces {
    private Place house;
    private Place bedroom;
    private Place diningRoom;
    private Place table;

    StubPlaces() {
        this.house = null;
        this.bedroom = null;
        this.diningRoom = null;
        this.table = null;
    }

    Place getHouse() {
        if (house == null) {
            this.house = new Place("house");
        }
        return this.house;
    }

    Place getBedroom() {
        if (bedroom == null) {
            this.bedroom = new Place("bedroom", getHouse());
        }
        return this.bedroom;
    }

    Place getDiningRoom() {
        if (diningRoom == null) {
            this.diningRoom = new Place("dining room", getHouse());
        }
        return this.diningRoom;
    }

    Place getTable() {
        if (table == null) {
            this.table = new Place("table", getDiningRoom());
        }
        return this.table;
    }
}

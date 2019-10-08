package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

public class PersonBuilder {
    private String name;
    private Neck neck;
    private Place location;

    public PersonBuilder withOneHead() {
        this.neck = new SingleHeadedNeck();
        return this;
    }

    public PersonBuilder withTwoHeads() {
        this.neck = new DoubleHeadedNeck();
        return this;
    }

    public PersonBuilder locatedAt(Place location) {
        this.location = location;
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Person build() {
        return new Person(name, location, neck);
    }
}

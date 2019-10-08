package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {

    private final String name;
    private boolean isNervous;
    private boolean isStunned;
    private boolean isBelievingEyes;

    private Legs legs;
    private Neck neck;

    private Place location;
    private Person observedPerson;

    private Logger log = LoggerFactory.getLogger(Person.class);
    private Hand leftHand;

    public Person(String name, Place location, Neck neck) {
        this.name = name;

        this.isNervous = false;
        this.isStunned = false;
        this.isBelievingEyes = true;

        this.leftHand = new Hand();
        this.legs = new Legs(this);
        this.neck = neck;

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
        log.info("{} looks at {}", this, someone);
        this.observedPerson = someone;
        if (someone.getNumberOfHeads() != this.getNumberOfHeads()) {
            this.isStunned = true;
        }
    }

    public Neck getNeck() {
        return this.neck;
    }

    private int getNumberOfHeads() {
        return neck.getNumberOfHeads();
    }

    public Place getLocation() {
        return this.location;
    }

    public boolean isLocatedAt(Place place) {
        return this.location.equals(place) ||
               this.location.isInside(place);
    }

    public void goTo(Place newLocation) {
        log.info("{} goes to {}", this, newLocation);
        this.location = newLocation;
        this.legs.setLocation(newLocation);
    }

    public void follow(Person whom) {
        log.info("{} follows {} into {}", this, whom, whom.getLocation());
        this.isNervous = true;
        this.goTo(whom.getLocation());
    }

    public void putLegsOnto(Place place) {
        log.info("{} puts their legs onto {}", this, place);
        legs.setLocation(place);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void watch(Things things) {
        this.isBelievingEyes = false;
        this.getNeck().getHeads().forEach(head -> {
            head.getJaws().hang();
        });
    }

    public Hand getLeftHand() {
        return this.leftHand;
    }

    public Legs getLegs() {
        return this.legs;
    }

    public String getName() {
        return this.name;
    }
}

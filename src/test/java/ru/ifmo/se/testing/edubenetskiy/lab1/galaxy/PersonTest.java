package ru.ifmo.se.testing.edubenetskiy.lab1.galaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ifmo.se.testing.edubenetskiy.lab1.galaxy.PersonAssert.assertThat;
import static ru.ifmo.se.testing.edubenetskiy.lab1.galaxy.LegsAssert.assertThat;

class PersonTest {

    private StubPlaces places;
    private Person testSubject;

    @BeforeEach
    void setUp() {
        this.places = new StubPlaces();

        this.testSubject = new PersonBuilder()
                .withName("Anton")
                .withOneHead()
                .locatedAt(places.getHouse())
                .build();
    }

    @Test
    void goTo_movesPerson() {
        Place diningRoom = places.getDiningRoom();
        testSubject.goTo(diningRoom);
        assertThat(testSubject.isLocatedAt(diningRoom)).isTrue();
    }

    @Test
    void getLocation_hasExactLocationWhereTheyWent() {
        testSubject.goTo(places.getBedroom());
        assertThat(testSubject).hasLocation(places.getBedroom());
    }

    @Test
    void isLocatedAt_trueForExactLocation() {
        testSubject.goTo(places.getDiningRoom());
        assertThat(testSubject.isLocatedAt(places.getDiningRoom())).isTrue();
    }

    @Test
    void isLocatedAt_trueForParents() {
        testSubject.goTo(places.getDiningRoom());
        assertThat(testSubject.isLocatedAt(places.getHouse())).isTrue();
    }

    @Test
    void goTo_movesPersonLegs() {
        Place bedroom = places.getBedroom();
        testSubject.goTo(bedroom);
        assertThat(testSubject.getLegs()).hasLocation(bedroom);
    }

    @Test
    void toString_equalsName() {
        assertThat(testSubject).hasToString(testSubject.getName());
    }
}

package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.LegsAssert.assertThat;
import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.JawsAssert.assertThat;
import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.PersonAssert.assertThat;

class PersonTest {

    private PlaceStubs places;
    private PersonStubs persons;
    private Person testSubject;

    @BeforeEach
    void setUp() {
        this.places = new PlaceStubs();
        this.persons = new PersonStubs(places);

        this.testSubject = new PersonBuilder()
                .withName("Anton")
                .withOneHead()
                .locatedAt(places.getHouse())
                .build();
    }

    @Test
    void new_isNotNervous() {
        assertThat(testSubject).isNotNervous();
    }

    @Test
    void new_isNotStunned() {
        assertThat(testSubject).isNotStunned();
    }

    @Test
    void new_isBelievingEyes() {
        assertThat(testSubject).isBelievingEyes();
    }

    @Test
    void follow_changesLocation() {
        Person person = persons.withOneHead();
        testSubject.follow(person);
        assertThat(testSubject).hasLocation(places.getDiningRoom());
    }

    @Test
    void follow_makesSelfNervous() {
        Person person = persons.withOneHead();
        testSubject.follow(person);
        assertThat(testSubject).isNervous();
    }

    @Test
    void lookAt_somebodyWithSameNumberOfHeads_doesNotMakeStunned() {
        Person person = persons.withOneHead();
        testSubject.lookAt(person);
        assertThat(testSubject).isNotStunned();
    }

    @Test
    void lookAt_somebodyWithOtherNumberOfHeads_makesStunned() {
        Person person = persons.withTwoHeads();
        testSubject.lookAt(person);
        assertThat(testSubject).isStunned();
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

    @Test
    void putLegsOnto_placeDescendantOfCurrentLocation_movesLegs() {
        testSubject.goTo(places.getDiningRoom());
        testSubject.putLegsOnto(places.getTableInDiningRoom());
        assertThat(testSubject.getLegs()).hasLocation(places.getTableInDiningRoom());
    }

    @Test
    void putLegsOnto_placeNotDescendantOfCurrentLocation_throws() {
        testSubject.goTo(places.getBedroom());
        assertThatCode(() -> testSubject.putLegsOnto(places.getTableInDiningRoom()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot put legs onto table: it is not inside " +
                        "bedroom where body is located");
    }

    @Test
    void watch_makesBelieveEyes() {
        testSubject.watch(new Things());
        assertThat(testSubject).isNotBelievingEyes();
    }

    @Test
    void watch_withOneHead_hangsAllJaws() {
        testSubject.watch(new Things());
        SingleHeadedNeck neck = (SingleHeadedNeck) testSubject.getNeck();
        assertThat(neck.getHead().getJaws()).isHanging();
    }

    @Test
    void watch_withTwoHeads_hangsAllJaws() {
        Person person = persons.withTwoHeads();
        person.watch(new Things());

        DoubleHeadedNeck neck = (DoubleHeadedNeck) person.getNeck();
        assertThat(neck.getLeftHead().getJaws()).isHanging();
        assertThat(neck.getRightHead().getJaws()).isHanging();
    }
}

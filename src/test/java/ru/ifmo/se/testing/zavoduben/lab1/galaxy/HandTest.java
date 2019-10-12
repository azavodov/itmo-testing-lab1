package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.JawsAssert.assertThat;

class HandTest {

    private Hand testSubject;
    private Person person;

    @BeforeEach
    void setUp() {
        this.person = new PersonStubs(new PlaceStubs()).withOneHead();
        this.testSubject = person.getLeftHand();
    }

    @Test
    void pickTeeth_makesTeethClean() {
        Jaws jaws = ((SingleHeadedNeck) person.getNeck()).getHead().getJaws();
        assertThat(jaws).doesNotHaveCleanTeeth();

        testSubject.pickTeeth(jaws);
        assertThat(jaws).hasCleanTeeth();
    }
}

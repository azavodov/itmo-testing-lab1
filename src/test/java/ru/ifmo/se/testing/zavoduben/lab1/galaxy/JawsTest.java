package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.JawsAssert.assertThat;

class JawsTest {
    private Jaws testSubject;

    @BeforeEach
    void setUp() {
        Person person = new PersonStubs(new PlaceStubs()).withOneHead();
        SingleHeadedNeck neck = (SingleHeadedNeck) person.getNeck();
        Head head = neck.getHead();
        this.testSubject = head.getJaws();
    }

    @Test
    void new_hasDirtyTeeth() {
        assertThat(testSubject).doesNotHaveCleanTeeth();
    }

    @Test
    void new_isNotHang() {
        assertThat(testSubject).isNotHanging();
    }

    @Test
    void hang_makesItHanging() {
        testSubject.hang();
        assertThat(testSubject).isHanging();
    }

    @Test
    void makeTeethClean_makesTeethClean() {
        testSubject.makeTeethClean();
        assertThat(testSubject).hasCleanTeeth();
    }
}

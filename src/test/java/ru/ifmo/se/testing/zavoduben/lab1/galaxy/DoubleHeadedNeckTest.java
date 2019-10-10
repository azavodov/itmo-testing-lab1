package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.DoubleHeadedNeckAssert.assertThat;

class DoubleHeadedNeckTest {

    private DoubleHeadedNeck testSubject;

    @BeforeEach
    void setUp() {
        this.testSubject = new DoubleHeadedNeck();
    }

    @Test
    void getNumberOfHeads_isTwo() {
        assertThat(testSubject).hasNumberOfHeads(2);
    }

    @Test
    void getHeads_exactlyLeftAndRight() {
        assertThat(testSubject).hasOnlyHeads(
                testSubject.getLeftHead(),
                testSubject.getRightHead()
        );
    }

    @Test
    @Disabled
    void getLeftHead_isLeftHead() {
    }

    @Test
    @Disabled
    void getRightHead_isRightHead() {

    }
}

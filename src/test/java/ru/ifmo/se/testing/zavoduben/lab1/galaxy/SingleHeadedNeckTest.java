package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.SingleHeadedNeckAssert.assertThat;

class SingleHeadedNeckTest {

    private SingleHeadedNeck testSubject;

    @BeforeEach
    void setUp() {
        this.testSubject = new SingleHeadedNeck();
    }

    @Test
    void getNumberOfHeads_isOne() {
        assertThat(testSubject).hasNumberOfHeads(1);
    }

    @Test
    void getHeads_exactlySingleHead() {
        assertThat(testSubject).hasOnlyHeads(
                testSubject.getHead()
        );
    }
}

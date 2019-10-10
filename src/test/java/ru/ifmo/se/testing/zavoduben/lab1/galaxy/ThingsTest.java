package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.ThingsAssert.assertThat;

class ThingsTest {

    private Things testSubject;

    @BeforeEach
    void setUp() {
        this.testSubject = new Things();
    }

    @Test
    void getNumber_returnsZero() {
        assertThat(testSubject).hasNumber(0);
    }
}

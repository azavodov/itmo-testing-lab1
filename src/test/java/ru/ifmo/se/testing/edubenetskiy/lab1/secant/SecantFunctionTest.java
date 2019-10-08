package ru.ifmo.se.testing.edubenetskiy.lab1.secant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SecantFunctionTest {

    private SecantFunction testSubject;

    @BeforeEach
    void setUp() {
        this.testSubject = new SecantFunction(5);
    }

    @Test
    void getValue_atZero_isPlusOne() {
        assertThat(testSubject.getValue(0.0D)).isCloseTo(+1.0D, within(0.1D));
    }

    @Test
    void getValue_uptoPlusHalfPi_isPositive() {
        double x = Math.random() * Math.PI / 2;
        assertThat(testSubject.getValue(x))
                .isCloseTo(1 / Math.cos(x), withinPercentage(10L));
    }

    @Test
    void getValue_downtoMinusHalfPi_isNegative() {
        double x = -(Math.random() * Math.PI / 2);
        double actualValue = testSubject.getValue(x);
        double expectedValue = 1 / Math.cos(x);

        assertThat(actualValue).isNegative();
        assertThat(actualValue).isCloseTo(expectedValue, withinPercentage(10L));
    }
}

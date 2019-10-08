package ru.ifmo.se.testing.edubenetskiy.lab1.galaxy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ru.ifmo.se.testing.edubenetskiy.lab1.galaxy.EyesAssert.assertThat;

class EyesTest {

    private static Random random;

    @BeforeAll
    static void setUp() {
        random = new Random();
    }

    @Test
    void test_new_cannotHaveZeroEyes() {
        assertThatThrownBy(() -> new Eyes(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("person should have positive number of eyes, you provided 0");
    }

    @Test
    void test_new_cannotHaveNegativeNumberOfEyes() {
        int numEyes = -random.nextInt(Integer.MAX_VALUE);
        assertThatThrownBy(() -> new Eyes(numEyes))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("person should have positive number of eyes, you provided " + numEyes);
    }

    @Test
    void test_new_canHaveOneEye() {
        int numEyes = 1;
        assertThatCode(() -> new Eyes(numEyes))
                .doesNotThrowAnyException();
    }

    @Test
    void test_new_canHavePositiveNumberOfEyes() {
        int numEyes = random.nextInt(Integer.MAX_VALUE - 2) + 2;
        assertThatCode(() -> new Eyes(numEyes))
                .doesNotThrowAnyException();
    }

    @Test
    void test_getNumber_returnsNumberFromConstructor() {
        int numEyes = random.nextInt(Integer.MAX_VALUE - 1) + 1;
        Eyes eyes = new Eyes(numEyes);
        assertThat(eyes).hasNumber(numEyes);
    }
}

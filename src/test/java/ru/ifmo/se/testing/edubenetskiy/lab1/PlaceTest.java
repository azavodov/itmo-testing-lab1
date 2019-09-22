package ru.ifmo.se.testing.edubenetskiy.lab1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlaceTest {
    @Test
    void test_new_cannotHaveEmptyName() {
        assertThatThrownBy(() -> new Place(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name of place should not be empty");
    }

    @Test
    void test_new_cannotHaveEmptyNameWithParent() {
        Place house = new Place("house");
        assertThatThrownBy(() -> new Place("", house))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name of place should not be empty");

    }

    @Test
    void test_new_canHaveNoParent() {
        Place house = new Place("house");
        assertThat(house.getParentPlace()).isNull();
    }

    @Test
    void test_new_canHaveParent() {
        Place house = new Place("house");
        Place room = new Place("room", house);
        assertThat(room.getParentPlace()).isSameAs(house);
    }
}
package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        PlaceAssert.assertThat(house.getParentPlace()).isNull();
    }

    @Test
    void test_new_canHaveParent() {
        Place house = new Place("house");
        Place room = new Place("room", house);
        PlaceAssert.assertThat(room.getParentPlace()).isSameAs(house);
    }

    @Test
    void test_isInside_falseForItself() {
        Place room = new Place("room");
        assertThat(room.isInside(room)).isFalse();
    }

    @Test
    void test_isInside_falseForChild() {
        Place house = new Place("house");
        Place room = new Place("room", house);
        assertThat(house.isInside(room)).isFalse();
    }

    @Test
    void test_isInside_falseForIndirectDescendant() {
        Place house = new Place("house");
        Place room = new Place("room", house);
        Place corner = new Place("corner", room);
        assertThat(house.isInside(corner)).isFalse();
    }

    @Test
    void test_isInside_trueForParent() {
        Place house = new Place("house");
        Place room = new Place("room", house);
        assertThat(room.isInside(house)).isTrue();
    }

    @Test
    void test_isInside_trueForIndirectAncestor() {
        Place house = new Place("house");
        Place room = new Place("room", house);
        Place corner = new Place("corner", room);
        assertThat(corner.isInside(house)).isTrue();
    }

    @Test
    void getName_returnsNameFromConstructor() {
        Place room = new Place("room");
        PlaceAssert.assertThat(room).hasName("room");
    }

    @Test
    void testToString_returnsName() {
        Place room = new Place("room");
        PlaceAssert.assertThat(room).hasToString("room");
    }
}

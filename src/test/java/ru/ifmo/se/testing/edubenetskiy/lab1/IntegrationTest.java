package ru.ifmo.se.testing.edubenetskiy.lab1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ifmo.se.testing.edubenetskiy.lab1.PersonAssert.assertThat;

public class IntegrationTest {

    @Test
    void main() {
        Place outside = new Place("outside");
        Place room = new Place("the room");
        Place armchair = new Place("the armchair", room);
        Place controlPanel = new Place("the control panel", room);

        Person charlie = new PersonBuilder()
                .withName("Charlie")
                .withTwoHeads()
                .locatedAt(armchair)
                .build();

        charlie.putLegsOnto(controlPanel);

        Person boris = new Person("Boris", outside);
        boris.goTo(room);

        Person arthur = new Person("Arthur", outside);

        arthur.follow(boris);
        assertThat(arthur).hasLocation(room);
        assertThat(arthur).isNervous();
        assertThat(arthur).isNotStunned();

        arthur.lookAt(charlie);
        assertThat(arthur).isStunned();

    }
}

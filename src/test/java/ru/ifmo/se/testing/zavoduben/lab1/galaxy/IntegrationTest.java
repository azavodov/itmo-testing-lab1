package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import org.junit.jupiter.api.Test;

import static ru.ifmo.se.testing.zavoduben.lab1.galaxy.JawsAssert.assertThat;

class IntegrationTest {

    @Test
    void main() {
        Place outside = new Place("outside");
        Place room = new Place("the room");
        Place armchair = new Place("the armchair", room);
        Place controlPanel = new Place("the control panel", armchair);

        Person charlie = new PersonBuilder()
                .withName("Charlie")
                .withTwoHeads()
                .locatedAt(armchair)
                .build();

        DoubleHeadedNeck charlieNeck = (DoubleHeadedNeck) charlie.getNeck();

        charlie.putLegsOnto(controlPanel);
        LegsAssert.assertThat(charlie.getLegs()).hasLocation(controlPanel);

        charlie.getLeftHand().pickTeeth(charlieNeck.getRightHead().getJaws());
        assertThat(charlieNeck.getLeftHead().getJaws()).doesNotHaveCleanTeeth();
        assertThat(charlieNeck.getRightHead().getJaws()).hasCleanTeeth();

        Person boris = new PersonBuilder()
                .withName("Boris")
                .withOneHead()
                .locatedAt(outside)
                .build();

        boris.goTo(room);

        Person arthur = new PersonBuilder()
                .withName("Arthur")
                .withOneHead()
                .locatedAt(outside)
                .build();

        SingleHeadedNeck arthurNeck = (SingleHeadedNeck) arthur.getNeck();

        arthur.follow(boris);
        PersonAssert.assertThat(arthur).hasLocation(room);
        PersonAssert.assertThat(arthur).isNervous();
        PersonAssert.assertThat(arthur).isNotStunned();

        arthur.lookAt(charlie);
        PersonAssert.assertThat(arthur).isStunned();
        PersonAssert.assertThat(arthur).isBelievingEyes();

        Things things = new Things();
        arthur.watch(things);
        PersonAssert.assertThat(arthur).isNotBelievingEyes();
        assertThat(arthurNeck.getHead().getJaws()).isHanging();
    }
}

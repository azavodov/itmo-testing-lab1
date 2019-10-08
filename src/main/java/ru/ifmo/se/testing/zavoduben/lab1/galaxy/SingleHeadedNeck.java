package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import java.util.Collections;
import java.util.Set;

public class SingleHeadedNeck extends Neck {
    private final Head head;

    @Override
    public int getNumberOfHeads() {
        return 1;
    }

    SingleHeadedNeck() {
        super();
        this.head = new Head(this);
    }

    Head getHead() {
        return this.head;
    }

    @Override
    public Set<Head> getHeads() {
        return Collections.singleton(this.head);
    }
}

package ru.ifmo.se.testing.edubenetskiy.lab1;

import java.util.HashSet;
import java.util.Set;

public class DoubleHeadedNeck extends Neck {

    private final Head leftHead;
    private final Head rightHead;

    DoubleHeadedNeck() {
        this.leftHead = new Head();
        this.rightHead = new Head();
    }

    @Override
    public int getNumberOfHeads() {
        return 2;
    }

    @Override
    public Set<Head> getHeads() {
        return new HashSet<>();
    }

    public Head getLeftHead() {
        return this.leftHead;
    }

    public Head getRightHead() {
        return this.rightHead;
    }
}

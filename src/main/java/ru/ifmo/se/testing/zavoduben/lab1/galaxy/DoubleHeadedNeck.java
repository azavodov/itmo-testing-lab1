package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DoubleHeadedNeck extends Neck {

    private final Head leftHead;
    private final Head rightHead;

    DoubleHeadedNeck() {
        this.leftHead = new Head(this);
        this.rightHead = new Head(this);
    }

    @Override
    public int getNumberOfHeads() {
        return 2;
    }

    @Override
    public Set<Head> getHeads() {
        return new HashSet<>(Arrays.asList(leftHead, rightHead));
    }

    public Head getLeftHead() {
        return this.leftHead;
    }

    public Head getRightHead() {
        return this.rightHead;
    }
}

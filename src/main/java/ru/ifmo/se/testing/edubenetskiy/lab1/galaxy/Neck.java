package ru.ifmo.se.testing.edubenetskiy.lab1.galaxy;

import java.util.Set;

public abstract class Neck {
    Neck() {
    }

    abstract public int getNumberOfHeads();

    abstract public Set<Head> getHeads();
}
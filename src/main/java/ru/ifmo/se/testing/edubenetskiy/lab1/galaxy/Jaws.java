package ru.ifmo.se.testing.edubenetskiy.lab1.galaxy;

public class Jaws {
    private boolean isHanging = false;
    private boolean hasCleanTeeth = false;

    public boolean isHanging() {
        return this.isHanging;
    }

    void hang() {
        this.isHanging = true;
    }

    public boolean hasCleanTeeth() {
        return this.hasCleanTeeth;
    }

    void makeTeethClean() {
        this.hasCleanTeeth = true;
    }
}

package ru.ifmo.se.testing.zavoduben.lab1.galaxy;

public class Eyes {
    private int number;

    Eyes(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("person should have positive number of eyes, you provided " + number);
        }

        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

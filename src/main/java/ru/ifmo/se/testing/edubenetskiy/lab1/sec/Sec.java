package ru.ifmo.se.testing.edubenetskiy.lab1.sec;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class Sec {

    private int n;

    public Sec(int n) {
        this.n = n;
    }

    // TODO maybe rewrite this shit?
    private double getEulerNumber(int i) {
        if (i % 2 != 0) {
            return 0;
        }
        switch (i) {
            case (0): return 1;
            case (2): return -1;
            case (4): return 5;
            case (6): return -61;
            case (8): return 1385;
            case (10): return -50521;
            case (12): return 2702765;
            case (14): return -199360981;
            default: throw new ValueException("The value is too long.");
        }
    }

    private double factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public double getValue(double x) {
        if (Math.abs(x) < Math.PI / 2)
            throw new ValueException("Incorrect value of x (|x| < pi / 2)");

        double y = 0;

        for (int i = 0; i < this.n; i++) {
            y += ( Math.pow(-1, i) * this.getEulerNumber(2 * i) * Math.pow(x, 2 * i)) /
                    ( this.factorial(2 * i) );
        }

        return y;
    }

    public static void main(String[] args) {
        Sec sec = new Sec(5);
        System.out.println(sec.getValue(555));
    }

}

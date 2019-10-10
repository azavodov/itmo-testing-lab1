package ru.ifmo.se.testing.zavoduben.lab1.secant;

import java.util.function.DoubleUnaryOperator;

public class SecantFunction implements DoubleUnaryOperator {

    private int numTerms;

    public SecantFunction(int numTerms) {
        this.numTerms = numTerms;
    }

    @Override
    public double applyAsDouble(double x) {
        return getValue(x);
    }

    public double getValue(double x) {
        if (Double.isInfinite(x)) {
            throw new IllegalArgumentException("Secant is not defined at infinity");
        }

        if (Double.isNaN(x)) {
            throw new IllegalArgumentException("Secant is not defined for NaN");
        }

        double y = 0;

        for (int i = 0; i < this.numTerms; i++) {
            y += (Math.pow(-1, i) * Math.pow(x, 2 * i) * this.getEulerNumber(2 * i)) /
                 (this.factorial(2 * i));
        }

        return y;
    }

    // TODO maybe rewrite this shit?
    private double getEulerNumber(int i) {
        if (i % 2 != 0) {
            return 0;
        }
        switch (i) {
            case (0):
                return 1;
            case (2):
                return -1;
            case (4):
                return 5;
            case (6):
                return -61;
            case (8):
                return 1385;
            case (10):
                return -50521;
            case (12):
                return 2702765;
            case (14):
                return -199360981;
            case (16):
                return 19391512145.;
            case (18):
                return -2404879675441.;
            case (20):
                return 370371188237525.;
            case (22):
                return -69348874393137901.;
            case (24):
                return 15514534163557086905.;
            case (26):
                return -4087072509293123892361.;
            case (28):
                return 1252259641403629865468285.;
            case (30):
                return -441543893249023104553682821.;
            default:
                throw new IllegalArgumentException("The value is too long.");
        }
    }

    private double factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public static void main(String[] args) {
        SecantFunction secantFunction = new SecantFunction(5);
        for (double x = -5.0; x <= 5.0; x += 0.1) {
            System.out.printf("%.2f -> % .15f%n", x, secantFunction.getValue(x));
            System.out.printf("%.2f => % .15f%n", x, 1 / Math.cos(x));
            System.out.println("-----------------------------------");
        }
    }

}

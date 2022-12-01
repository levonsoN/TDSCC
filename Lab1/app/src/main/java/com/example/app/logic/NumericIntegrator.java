package com.example.app.logic;

public class NumericIntegrator {
    private double a;
    private double b;
    private long n;
    private Function function;

    public NumericIntegrator(Function function, double a, double b, long n) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.function = function;
    }

    public double integrate() {
        double result = 0, h = (b - a) / n;
        for (long i = 0; i < n; i++)
            result += function.f(a + h * i) * h;
        return result;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

}

package com.example.app.logic;

public class LabFunction implements Function {
    @Override
    public double f(double x) {
        double t = Math.sin(2 * x);
        return 1. / (t * t);
    }
}

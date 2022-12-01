package com.example.app.logic;

public class NumericIntegratorRunnableContainer implements Runnable {
    private final NumericIntegrator integrator;
    private double result;
    private long timeElapsed;

    public NumericIntegratorRunnableContainer(NumericIntegrator integrator) {
        this.integrator = integrator;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        result = integrator.integrate();
        timeElapsed = System.nanoTime() - startTime;
    }

    public NumericIntegrator getIntegrator() {
        return integrator;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public double getResult() {
        return result;
    }
}

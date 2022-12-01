package com.example.app.logic;

import java.util.Objects;
import java.util.concurrent.Callable;

public final class NumericIntegratorCallableContainer implements Callable<NumericIntegrationResult> {
    private final NumericIntegrator integrator;

    public NumericIntegratorCallableContainer(
            NumericIntegrator integrator) {
        this.integrator = integrator;
    }

    @Override
    public NumericIntegrationResult call() throws Exception {
        long startTime = System.nanoTime();
        double result = integrator.integrate();
        return new NumericIntegrationResult(result, System.nanoTime() - startTime);
    }

    public NumericIntegrator getIntegrator() {
        return integrator;
    }
}


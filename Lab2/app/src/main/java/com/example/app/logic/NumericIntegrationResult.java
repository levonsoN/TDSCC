package com.example.app.logic;

import java.util.Objects;

public final class NumericIntegrationResult {
    private final double result;
    private final long timeElapsed;

    public NumericIntegrationResult(double result, long timeElapsed) {
        this.result = result;
        this.timeElapsed = timeElapsed;
    }

    public double getResult() {
        return result;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }
}

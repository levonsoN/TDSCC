package com.example.app.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumericIntegratorTest {
    private final NumericIntegrator integrator;

    public NumericIntegratorTest() {
        this.integrator = new NumericIntegrator(new LinearFunction(), 0, 1, 1000000);
    }

    @Test
    void integrate() {
        assertEquals(0.5, integrator.integrate(), 0.00001);
        integrator.setB(2);
        assertEquals(2, integrator.integrate(), 0.00001);
    }
}
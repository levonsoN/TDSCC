package com.example.app.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabFunctionTest {

    private final Function function;

    public LabFunctionTest() {
        this.function = new LabFunction();
    }
    @Test
    void f() {
        assertEquals(1.20945, function.f(1),0.00001);
        assertEquals(1.74596, function.f(2),0.00001);
        assertEquals(1.41228, function.f(0.5),0.00001);
        assertEquals(4.35068, function.f(0.25),0.00001);
    }
}
package com.example.app.logic;

public class Region {
    private final int id;
    private final String name;
    private final double area;

    public Region(int id, String name, double area) {
        this.id = id;
        this.name = name;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return getName();
    }
}

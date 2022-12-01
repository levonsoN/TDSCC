package com.example.app.logic;

import java.time.LocalDate;

public class WeatherData {
    private final int id;
    private final Region region;
    private final LocalDate date;
    private final double temperature;
    private final String precipitation;

    public WeatherData(int id, Region region, LocalDate date, double temperature, String precipitation) {
        this.id = id;
        this.region = region;
        this.date = date;
        this.temperature = temperature;
        this.precipitation = precipitation;
    }

    public int getId() {
        return id;
    }

    public Region getRegion() {
        return region;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getPrecipitation() {
        return precipitation;
    }
}

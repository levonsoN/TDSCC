package com.example.app.logic;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

public class DbHandler {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            new Driver();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherdb", "root", "1234");
        }
        return connection;
    }

    public static Region[] getRegions() throws SQLException {
        ArrayList<Region> list = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");
        while (resultSet.next())
            list.add(new Region(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3)));
        Region[] result = new Region[list.size()];
        list.toArray(result);
        return result;
    }

    public static int addWeatherData(Region region, LocalDate date, double temperature, String precipitation) throws SQLException {
        Statement statement = getConnection().createStatement();
        return statement.executeUpdate("INSERT INTO weathers (regionId, date, temperature, precipitation) VALUES " +
                "(" + region.getId() + ", '" + date + "', " + temperature + ", '" + precipitation + "')");
    }

    public static WeatherData getWeatherData(Region region, LocalDate date) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM weathers " +
                "INNER JOIN regions ON weathers.regionId = regions.regionId " +
                "WHERE weathers.regionId = " + region.getId() +
                " AND weathers.date = '" + date + "'");
        resultSet.next();
        return new WeatherData(resultSet.getInt(1), region, resultSet.getDate(3).toLocalDate(), resultSet.getDouble(4), resultSet.getString(5));
    }

    public static int updateWeatherData(Region region, LocalDate date, double temperature, String precipitation) throws SQLException {
        Statement statement = getConnection().createStatement();
        return statement.executeUpdate("UPDATE  weathers SET temperature = " + temperature + ", precipitation = '" + precipitation + "' " +
                "WHERE weathers.regionId = " + region.getId() +
                " AND weathers.date = '" + date + "'");
    }

    public static int deleteWeatherData(Region region, LocalDate date) throws SQLException {
        Statement statement = getConnection().createStatement();
        return statement.executeUpdate("DELETE  FROM weathers " +
                "WHERE weathers.regionId = " + region.getId() +
                " AND weathers.date = '" + date + "'");
    }
}

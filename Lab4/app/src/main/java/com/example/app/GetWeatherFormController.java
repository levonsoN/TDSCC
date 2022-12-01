package com.example.app;

import com.example.app.logic.DbHandler;
import com.example.app.logic.Region;
import com.example.app.logic.Util;
import com.example.app.logic.WeatherData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.time.LocalDate;

public class GetWeatherFormController {
    public AnchorPane anchor;
    public DatePicker dateBox;
    public ComboBox<Region> regionBox;
    public Button getButton;
    public WebView outputView;

    @FXML
    public void initialize() {
        try {
            regionBox.getItems().addAll(DbHandler.getRegions());
            regionBox.getSelectionModel().select(0);
            dateBox.setValue(LocalDate.parse("2022-01-01"));
        } catch (Exception e) {
            Util.ShowErrorAlert(e.getMessage());
            ((Stage) anchor.getScene().getWindow()).close();
        }
    }

    @FXML
    public void getClicked() {
        try {
            WeatherData data = DbHandler.getWeatherData(regionBox.getValue(), dateBox.getValue());
            String output = "<table><tr><th>Region</th><th>Date</th><th>Temperature</th><th>Precipitation</th></tr>" +
                    "<tr><td>" + data.getRegion().getName() + "</td><td>" + data.getDate() + "</td><td>" + data.getTemperature() + "</td><td>" + data.getPrecipitation() + "</td></tr></table>";
            outputView.getEngine().loadContent(output);
        } catch (Exception e) {
            Util.ShowErrorAlert(e.getMessage());
        }
    }
}

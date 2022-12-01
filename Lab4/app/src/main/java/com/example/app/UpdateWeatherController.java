package com.example.app;

import com.example.app.logic.DbHandler;
import com.example.app.logic.Region;
import com.example.app.logic.Util;
import com.example.app.logic.WeatherData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class UpdateWeatherController {
    public AnchorPane anchor;
    public Button updateButton;
    public DatePicker dateBox;
    public ComboBox<Region> regionBox;
    public Spinner<Double> tempBox;
    public ComboBox<String> precipBox;

    @FXML

    public void initialize() {
        try {
            tempBox.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-40, 40, 0, 0.01));
            precipBox.getItems().addAll("none", "rain", "snow", "steel", "hail");
            precipBox.getSelectionModel().select(0);
            regionBox.getItems().addAll(DbHandler.getRegions());
            regionBox.getSelectionModel().select(0);
            dateBox.setValue(LocalDate.parse("2022-01-01"));
        } catch (Exception e) {
            Util.ShowErrorAlert(e.getMessage());
            ((Stage) anchor.getScene().getWindow()).close();
        }
    }

    @FXML
    public void updateClicked() {
        try {
            DbHandler.updateWeatherData(regionBox.getValue(), dateBox.getValue(),tempBox.getValue(),precipBox.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Weather data has been updated successfully.");
            alert.show();
        } catch (Exception e) {
            Util.ShowErrorAlert(e.getMessage());
        }
    }
}

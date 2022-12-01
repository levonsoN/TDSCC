package com.example.app;

import com.example.app.logic.DbHandler;
import com.example.app.logic.Util;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DeleteWeatherFormController {
    public AnchorPane anchor;
    public Button deleteButton;
    public DatePicker dateBox;
    public ComboBox<com.example.app.logic.Region> regionBox;

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
    public void updateClicked() {
        try {
            DbHandler.deleteWeatherData(regionBox.getValue(), dateBox.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Weather data has been deleted successfully.");
            alert.show();
        } catch (Exception e) {
            Util.ShowErrorAlert(e.getMessage());
        }
    }
}

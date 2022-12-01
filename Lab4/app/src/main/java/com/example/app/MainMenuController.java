package com.example.app;

import com.example.app.logic.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainMenuController {
    public AnchorPane anchor;
    public Button customButton;
    public Button addButton;
    public Button getButton;
    public Button editButton;
    public Button deleteButton;

    @FXML
    public void customClicked() {
        showStage("customQueryForm.fxml", "Execute Custom Query");
    }

    @FXML

    public void addClicked() {
        showStage("addWeatherForm.fxml", "Add Weather Data");
    }

    @FXML

    public void getClicked() {
        showStage("getWeatherForm.fxml", "Get Weather Data");
    }

    @FXML

    public void editClicked() {
        showStage("updateWeatherForm.fxml", "Edit Weather Data");
    }

    @FXML

    public void deleteClicked() {
        showStage("deleteWeatherForm.fxml", "Edit Weather Data");
    }

    private void showStage(String stagePath, String title) {

        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(new FXMLLoader(Application.class.getResource(stagePath)).load()));
            stage.setResizable(false);
            stage.show();
            Application.getMainMenuStage().hide();
            stage.setOnCloseRequest((WindowEvent event) -> {
                Application.getMainMenuStage().show();
            });
        } catch (Exception e) {
            Util.ShowErrorAlert(e.getMessage());
        }
    }
}

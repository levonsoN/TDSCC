package com.example.app.logic;

import javafx.scene.control.Alert;

public class Util {
    public static void ShowErrorAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unexpected Exception");
        alert.setHeaderText("Unexpected Exception");
        alert.setContentText(message);
        alert.show();
    }
}

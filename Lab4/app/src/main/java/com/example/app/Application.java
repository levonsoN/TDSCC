package com.example.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Stage mainMenuStage;

    public static Stage getMainMenuStage() {
        return mainMenuStage;
    }

    @Override
    public void start(Stage mainMenuStage) throws IOException {
        mainMenuStage.setTitle("Main Menu");
        mainMenuStage.setScene(new Scene(new FXMLLoader(Application.class.getResource("mainMenu.fxml")).load()));
        mainMenuStage.setResizable(false);
        mainMenuStage.show();

        Application.mainMenuStage = mainMenuStage;
    }

    public static void main(String[] args) {
        launch();
    }
}
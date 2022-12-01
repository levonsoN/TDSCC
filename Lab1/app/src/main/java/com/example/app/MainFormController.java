package com.example.app;

import com.example.app.logic.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainFormController {
    public TextArea outputArea;
    public TextField aText;
    public TextField bText;
    public TextField nText;
    public TextField cText;
    public Button calculateButton;

    @FXML
    private void calculateButtonClick() {
        outputArea.setText("");
        double a, b;
        long totalStepsCount;
        int threadsCount;
        try {
            a = Double.parseDouble(aText.getText());
            b = Double.parseDouble(bText.getText());
            totalStepsCount = Long.parseLong(nText.getText());
            threadsCount = Integer.parseInt(cText.getText());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText(ex.getMessage());
            return;
        }
        double rangePerThread = (b - a) / threadsCount;
        long stepsPerThread = totalStepsCount / threadsCount;
        NumericIntegratorRunnableContainer[] containers = new NumericIntegratorRunnableContainer[threadsCount];
        Thread[] threads = new Thread[threadsCount];
        long time = System.nanoTime();
        double result = 0;
        for (int i = 0; i < containers.length; i++) {
            containers[i] = new NumericIntegratorRunnableContainer(new NumericIntegrator(
                    new LinearFunction(),
                    a + i * rangePerThread,
                    a + (i + 1) * rangePerThread,
                    stepsPerThread));
            threads[i] = new Thread(containers[i]);
            threads[i].run();
        }
        try {
            for (int i = 0; i < containers.length; i++)
                threads[i].join();
            time = System.nanoTime() - time;
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Exception");
            alert.setHeaderText("Unexpected Exception");
            alert.setContentText(ex.getMessage());
            return;
        }
        for (int i = 0; i < containers.length; i++) {
            result += containers[i].getResult();
            outputArea.appendText(String.format("Thread #%d; Result: %f; Time Elapsed: %fms\n",
                    i + 1,
                    containers[i].getResult(),
                    containers[i].getTimeElapsed() / 1000000.));
        }
        outputArea.appendText(String.format("Final Result: %f; Total Elapsed Time: %fms\n", result, time / 1000000.));
    }
}
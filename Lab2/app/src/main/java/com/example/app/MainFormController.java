package com.example.app;

import com.example.app.logic.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainFormController {
    private ExecutorService service;
    public TextArea outputArea;
    public TextField aText;
    public TextField bText;
    public TextField nText;
    public TextField cText;
    public Button calculateButton;

    @FXML
    private void initialize() {
        service = Executors.newFixedThreadPool(8);
    }

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
        NumericIntegratorCallableContainer[] containers = new NumericIntegratorCallableContainer[threadsCount];
        NumericIntegrationResult[] results = new NumericIntegrationResult[threadsCount];
        Future<NumericIntegrationResult>[] futures = new Future[threadsCount];
        long time = System.nanoTime();
        for (int i = 0; i < containers.length; i++) {
            containers[i] = new NumericIntegratorCallableContainer(new NumericIntegrator(new LinearFunction(), a + i * rangePerThread, a + (i + 1) * rangePerThread, stepsPerThread));
            futures[i] = service.submit(containers[i]);
        }
        double result = 0;
        try {
            for (int i = 0; i < containers.length; i++)
                results[i] = futures[i].get();
            time = System.nanoTime() - time;
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Exception");
            alert.setHeaderText("Unexpected Exception");
            alert.setContentText(ex.getMessage());
            return;
        }
        for (int i = 0; i < results.length; i++) {
            result += results[i].getResult();
            outputArea.appendText(String.format("Thread #%d; Result: %f; Time Elapsed: %fms\n", i + 1, results[i].getResult(), results[i].getTimeElapsed() / 1000000.));
        }
        outputArea.appendText(String.format("Final Result: %f; Total Elapsed Time: %fms\n", result, time / 1000000.));
    }
}
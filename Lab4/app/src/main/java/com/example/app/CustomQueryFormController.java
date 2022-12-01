package com.example.app;

import com.example.app.logic.DbHandler;
import com.example.app.logic.Util;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;

import java.sql.*;

public class CustomQueryFormController {
    public TextArea queryTextArea;
    public Button executeButton;
    public WebView outputView;
    @FXML
    public void executeQueryClick() {
        try {
            Connection connection = DbHandler.getConnection();
            Statement statement = connection.createStatement();
            if (!statement.execute(queryTextArea.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Result");
                alert.setHeaderText("Query executed successfully");
                alert.setContentText(statement.getUpdateCount() + " rows affected.");
                alert.show();
            } else {
                String output = "<table><tr>";
                ResultSet result = statement.getResultSet();
                ResultSetMetaData metaData = result.getMetaData();
                int columnsCount = metaData.getColumnCount();
                for (int i = 0; i < columnsCount; i++)
                    output += "<th>" + metaData.getColumnLabel(i + 1) + "</th>";
                output += "</tr>";
                while (result.next()) {
                    output += "<tr>";
                    for (int i = 0; i < columnsCount; i++)
                        output += "<td>" + result.getObject(i + 1).toString() + "</td>";
                    output += "</tr>";
                }
                output += "</table>";
                outputView.getEngine().loadContent(output);
            }
        } catch (Exception e) {
            Util.ShowErrorAlert(e.getMessage());
        }
    }
}

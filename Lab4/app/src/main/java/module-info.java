module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.web;


    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.app.logic;
    opens com.example.app.logic to javafx.fxml;
}
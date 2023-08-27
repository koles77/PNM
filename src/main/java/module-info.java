module com.example.pognm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.net.http;


    opens com.example.pognm to javafx.fxml;
    exports com.example.pognm;
    exports com.example.pognm.controllers;
    opens com.example.pognm.controllers to javafx.fxml;
}
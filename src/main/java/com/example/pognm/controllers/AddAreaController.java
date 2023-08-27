package com.example.pognm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.pognm.Application;
import com.example.pognm.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddAreaController extends Application {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    public TextArea addAreaName;
    @FXML
    private TextArea addAreaNote;
    @FXML
    private Button cancelBtnAddArea;
    @FXML
    private Button okBtnAddArea;

    private ArrayList listOfAreas = new ArrayList<>();

    @FXML
    void initialize() throws Exception{
        DatabaseHandler dbh = new DatabaseHandler();
        ResultSet res = dbh.getAreas();
        while (res.next()) {
            listOfAreas.add(res.getString(2));
        }
        okBtnAddArea.setOnAction(actionEvent -> {
            dbh.addArea(addAreaName.getText(), addAreaNote.getText());
            if (listOfAreas.contains(addAreaName.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Ебать ты оригинальный! Попрубуем другое название???");

                alert.showAndWait();
            }
            Stage ss = (Stage) okBtnAddArea.getScene().getWindow();
            ss.close();

            // Запускаем новое основное окно
            Stage stage = new Stage();
            Application app = new Application();
            try {
                app.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelBtnAddArea.setOnAction(actionEvent -> {
            Stage ss = (Stage) cancelBtnAddArea.getScene().getWindow();
            ss.close();

            // Запускаем новое основное окно
            Stage stage = new Stage();
            Application app = new Application();
            try {
                app.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

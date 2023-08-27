package com.example.pognm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.pognm.DatabaseHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller<btnField, addArCont> {

    @FXML
    private ResourceBundle resources;
    @FXML
    private FlowPane btnField = new FlowPane();
    @FXML
    private URL location;
    @FXML
    private Button addAreaButton;

    protected Controller controller;
    public static String btnIdForLocName;


    @FXML
    void initialize() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet res = dbHandler.getAreas();
        btnField.setHgap(30.0);
        btnField.setVgap(50.0);
        btnField.setAlignment(Pos.TOP_LEFT);

        while (res.next()) {
            Button btn = new Button(res.getString(2));
            btn.setId(res.getString(2));

            btn.setPrefSize(200,100);
            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(btn.getId());
                    btnIdForLocName = btn.getId();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation((getClass().getResource("/com/example/pognm/areaPCs.fxml")));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent rootR = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(rootR));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();

                    Stage ss = (Stage) btn.getScene().getWindow();
                    ss.close();
                }
            });
            btnField.getChildren().add(btn);
        }
        addAreaButton.setOnAction(actionEvent -> {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/pognm/addArea.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                    // гасим основное окно
                    Stage ss = (Stage) addAreaButton.getScene().getWindow();
                    ss.close();
            });
        }
}

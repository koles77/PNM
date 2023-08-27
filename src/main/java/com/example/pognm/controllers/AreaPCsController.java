package com.example.pognm.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.pognm.Application;
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


public class AreaPCsController extends Application{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FlowPane areaPcField = new FlowPane();

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button backButton;

    public static Integer idForInfo = null;

    @FXML
    void initialize() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet res = dbHandler.getPc(Controller.btnIdForLocName);
        areaPcField.setHgap(30.0);
        areaPcField.setVgap(50.0);
        areaPcField.setAlignment(Pos.TOP_LEFT);

        while (res.next()) {
            Button btn = new Button(res.getString(3));
            btn.setId(res.getString(1));
            btn.setPrefSize(100,50);
            areaPcField.getChildren().add(btn);
            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Stage ss = (Stage) btn.getScene().getWindow();
                    ss.close();

                    idForInfo = Integer.valueOf(btn.getId());
                    FXMLLoader loader = new FXMLLoader();
                    try {
                        loader.setLocation((getClass().getResource("/com/example/pognm/infoPC.fxml")));
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent rootR = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(rootR));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
            });
        }

        createButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/pognm/crudPC.fxml"));
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

            Stage ss = (Stage) createButton.getScene().getWindow();
            ss.close();
        });

        deleteButton.setOnAction(actionEvent -> {
            Stage ss = (Stage) deleteButton.getScene().getWindow();
            ss.close();
            Boolean resSetForPC = dbHandler.removePcFromDB(Controller.btnIdForLocName);
            Boolean resSetForArea = dbHandler.removeAreaFromDB(Controller.btnIdForLocName);

            // Запускаем новое основное окно
            Stage stage = new Stage();
            Application app = new Application();
            try {
                app.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> {
            Stage ss = (Stage) deleteButton.getScene().getWindow();
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

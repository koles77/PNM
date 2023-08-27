package com.example.pognm.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pognm.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class CrudPC extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButtonAddPc;

    @FXML
    private Label ipLabel;

    @FXML
    private Label ipLabel1;

    @FXML
    private TextArea ipTextArea;

    @FXML
    private TextArea locationName;

    @FXML
    private TextArea loginTextArea;

    @FXML
    private Label logoLabel;

    @FXML
    private Button okButtonAddPc;

    @FXML
    private TextArea pcNameArea;

    @FXML
    private Label pswdLabel;

    @FXML
    private TextArea pswdTextArea;

    @FXML
    void initialize() {
        locationName.setText(btnIdForLocName);

        okButtonAddPc.setOnAction(actionEvent -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            dbHandler.addPc(locationName.getText(), pcNameArea.getText(), ipTextArea.getText(), loginTextArea.getText(), pswdTextArea.getText());
            Button btn = new Button(ipTextArea.getText());
            btn.setId(ipTextArea.getText());
            btn.setPrefSize(200,100);

            Stage ss = (Stage) okButtonAddPc.getScene().getWindow();
            ss.close();

            Stage stage = new Stage();
            AreaPCsController app = new AreaPCsController();
            try {
                app.restartPCsArea(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelButtonAddPc.setOnAction(actionEvent -> {
            Stage ss = (Stage) cancelButtonAddPc.getScene().getWindow();
            ss.close();

            Stage stage = new Stage();
            AreaPCsController app = new AreaPCsController();
            try {
                app.restartPCsArea(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

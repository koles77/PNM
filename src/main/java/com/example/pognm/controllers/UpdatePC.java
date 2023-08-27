package com.example.pognm.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.pognm.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class UpdatePC extends InfoPCController {

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
    private Label logoLabel;

    @FXML
    private Button okButtonUpdatePc;

    @FXML
    private Label pswdLabel;

    @FXML
    private Label updatePcAreaName;

    @FXML
    private TextArea updatePcIpTextArea;

    @FXML
    private TextArea updatePcLogTextArea;

    @FXML
    private TextArea updatePcName;

    @FXML
    private TextArea updatePcPswdTextArea;

    @FXML
    void initialize() {
        DatabaseHandler handler = new DatabaseHandler();
        ArrayList list = handler.getInfoPcById(AreaPCsController.idForInfo);
        updatePcAreaName.setText(list.get(0).toString());
        updatePcName.setText(list.get(1).toString());
        updatePcIpTextArea.setText(list.get(2).toString());
        updatePcLogTextArea.setText(list.get(3).toString());
        updatePcPswdTextArea.setText(list.get(4).toString());

        okButtonUpdatePc.setOnAction(actionEvent -> {
            Stage ss = (Stage) okButtonUpdatePc.getScene().getWindow();
            ss.close();

            handler.updatePcByIdInDB(AreaPCsController.idForInfo, updatePcName.getText(), updatePcIpTextArea.getText(),
                    updatePcLogTextArea.getText(), updatePcPswdTextArea.getText());

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

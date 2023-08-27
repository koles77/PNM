package com.example.pognm.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.pognm.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoPCController extends CrudPC {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label areaInfoLabel;

    @FXML
    private Button deletePcButton;

    @FXML
    private Label ipInfoLabel;

    @FXML
    private Label ipLabel;

    @FXML
    private Label ipLabel1;

    @FXML
    private Label logoLabel;

    @FXML
    private Button okButtonAddPc;

    @FXML
    private Label pcInfoLogLabel;

    @FXML
    private Label pcInfoPswdLabel;

    @FXML
    private Label pcNameInfoLabel;

    @FXML
    private Label pswdLabel;

    @FXML
    private Button updateButtonAddPc;

    @FXML
    void initialize() {
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList list = dbh.getInfoPcById(AreaPCsController.idForInfo);
        areaInfoLabel.setText(list.get(0).toString());
        pcNameInfoLabel.setText(list.get(1).toString());
        ipInfoLabel.setText(list.get(2).toString());
        pcInfoLogLabel.setText(list.get(3).toString());
        pcInfoPswdLabel.setText(list.get(4).toString());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        deletePcButton.setOnAction(actionEvent -> {
            Stage ss = (Stage) deletePcButton.getScene().getWindow();
            ss.close();

            Boolean resSetForPC = dbh.removePcByIdFromDB(AreaPCsController.idForInfo);

            Stage stage = new Stage();
            AreaPCsController app = new AreaPCsController();
            try {
                app.restartPCsArea(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        okButtonAddPc.setOnAction(actionEvent -> {
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

        //допилить обновление данных
        updateButtonAddPc.setOnAction(actionEvent -> {
            Stage ss = (Stage) updateButtonAddPc.getScene().getWindow();
            ss.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/pognm/updatePC.fxml"));
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

        });
    }

}

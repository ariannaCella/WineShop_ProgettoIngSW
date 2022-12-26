package com.example.democlientserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseProposal {
    @FXML
    private TextField address;

    @FXML
    private CheckBox casse;

    @FXML
    private TextField id;

    @FXML
    private TextField number;

    @FXML
    private Button sandProposal;

    @FXML
    private TextField txtUser;

    @FXML
    protected void goHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeClient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Homepage!");
        stage.setScene(new Scene(root, 550, 700));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();

    }

    @FXML
    void makeProposal(ActionEvent event) {

    }

}

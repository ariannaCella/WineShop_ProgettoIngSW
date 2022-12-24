package com.example.democlientserver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;


public class HomeEmployee {

    @FXML
    private Button LogOut;

    @FXML
    private Button SearchWineName;

    @FXML
    private Button SearchWineYear;

    @FXML
    private Label SubTitle;

    @FXML
    private Label Title;

    @FXML
    private Button managDb;

    @FXML
    private Button modifyPassw;

    @FXML
    private Button searchPurchaseDate;

    @FXML
    private TextField txtWineName;

    @FXML
    private TextField txtWineYear;

    @FXML
    void LogOut(ActionEvent event) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Login ONLINE WINE SHOP");
        stage.setScene(new Scene(root, 500, 400));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void SearchWineName(ActionEvent event) {

    }

    @FXML
    void SearchWineYear(ActionEvent event) {

    }

    @FXML
    void managementDatabase(ActionEvent event) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("Management.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Management");
        stage.setScene(new Scene(root, 800, 800));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void modifyPassword(ActionEvent event) {

    }

    @FXML
    void searchPurchaseForDates(ActionEvent event) {

    }

    @FXML
    void textWineName(ActionEvent event) {

    }

    @FXML
    void textWineYear(ActionEvent event) {

    }

}

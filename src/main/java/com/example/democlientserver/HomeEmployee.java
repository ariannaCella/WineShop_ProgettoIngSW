package com.example.democlientserver;

import RequestResponse.RequestSearchWine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private Button SearchClientSurname;
    @FXML
    private TextField txtClientSurname;
    @FXML
    private TextField txtWine;
    @FXML
    private Button SearchWine;

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
    private ChoiceBox<String> ChoiceWine;
    @FXML
    private Label error;

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
    void SearchWine(ActionEvent event) throws IOException, ClassNotFoundException {
        String txt=txtWine.getText();
        if(txt.isBlank()){
            error.setVisible(true);
            error.setText("Inserire anno o nome del vino che vuoi ricercare");
            return;
        }
        String attribute=ChoiceWine.getValue();
        if(attribute==null){
            error.setVisible(true);
            error.setText("Scegli la ricerca per anno o per nome");
            return;
        }

        os.writeObject("searchWineEmployee");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            RequestSearchWine reqSerch = new RequestSearchWine(txt, attribute);
            os.writeObject(reqSerch);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            String message = (String) is.readObject();
            if (message.equals("View wines")) {
                Parent root = FXMLLoader.load(getClass().getResource("wineView.fxml"));
                Stage stage = new Stage();
                stage.setTitle("wines!");
                stage.setScene(new Scene(root, 600, 700));
                stage.setResizable(false);
                stage.show();
                Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisStage.hide();
            }
        }

    }

    @FXML
    void searchClientSurname(ActionEvent event) {

    }

    @FXML
    void managementDatabase(ActionEvent event) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("Management.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Management");
        stage.setScene(new Scene(root, 1008, 665));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void modifyPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Change Password");
        stage.setScene(new Scene(root, 612, 470));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
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

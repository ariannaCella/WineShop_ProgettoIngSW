package com.example.democlientserver;

import RequestResponse.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

import static com.example.democlientserver.HelloApplication.*;

public class Login {
    @FXML
    private Button enterlogin;
    private CheckBox isClient;

    @FXML
    private CheckBox isEmployee;

    @FXML
    private CheckBox isShipper;

    @FXML
    private CheckBox isSupplier;
    @FXML
    private Label error;
    @FXML
    private Hyperlink registrazione;
    @FXML
    private ChoiceBox <String> choiceTypeUser;
    @FXML
    private PasswordField textPass;

    @FXML
    private TextField textUser;

    @FXML
    private HBox userType;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onButtonClickOKLOGIN(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String txtInput=textUser.getText();
        String pswInput=textPass.getText();
        String table=choiceTypeUser.getValue();

        os.writeObject("Login");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }

        String o = (String) is.readObject();
        if(o.equals("OK")) {
            RequestLogin req = new RequestLogin(txtInput, pswInput, table);
            os.writeObject(req);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }

            String message = (String) is.readObject();
            if ((message.equals("Nome utente errato, riprova o registrati")) || (message.equals("Servizio momentaneamente fuori servizio! Ci scusiamo per il disagio") || (message.equals("Password errata, riprova")))) {
                error.setText(message);
            } else if (message.equals("OK")) {
                switch (table) {
                    case "client":
                        Parent rootClient = FXMLLoader.load(getClass().getResource("HomeClient.fxml"));
                        Stage stageClient = new Stage();
                        stageClient.setTitle("Homepage!");
                        stageClient.setScene(new Scene(rootClient, 550, 700));
                        stageClient.setResizable(false);
                        stageClient.show();
                        Stage thisStageClient = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        thisStageClient.hide();
                        break;
                    case "employee":
                        //if dipendente
                        /*if (is == null) {
                            is = new ObjectInputStream(new BufferedInputStream(
                                    client.getInputStream()));
                        }

                        message = (String) is.readObject();
                        if(message.equals("Employee")) {*/
                            Parent rootEmployee = FXMLLoader.load(getClass().getResource("HomeEmployee.fxml"));
                            Stage stageEmployee = new Stage();
                            stageEmployee.setTitle("Home Employee");
                            stageEmployee.setScene(new Scene(rootEmployee, 600, 400));
                            stageEmployee.setResizable(false);
                            stageEmployee.show();
                            Stage thisStageEmployee = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            thisStageEmployee.hide();/*
                        }
                        else if(message.equals("Administrator")){
                            Parent rootEmployee = FXMLLoader.load(getClass().getResource("HomeAdministrator.fxml"));
                            Stage stageEmployee = new Stage();
                            stageEmployee.setTitle("Home Administrator");
                            stageEmployee.setScene(new Scene(rootEmployee, 600, 400));
                            stageEmployee.setResizable(false);
                            stageEmployee.show();
                            Stage thisStageEmployee = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            thisStageEmployee.hide();
                        }*/
                        break;
                    /*
                    case "supplier":
                        Parent root = FXMLLoader.load(getClass().getResource("HomeSupplier.fxml"));
                        break;
                    case "shipper":
                        Parent root = FXMLLoader.load(getClass().getResource("HomeShipper.fxml"));
                        break; */
                }
            }
        }
    }


    @FXML
    protected void Registration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignInClient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("SignIn!");
        stage.setScene(new Scene(root, 335, 500));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();

    }

}
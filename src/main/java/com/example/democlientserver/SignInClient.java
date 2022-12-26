package com.example.democlientserver;

import Actors.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;

public class SignInClient {
    @FXML
    private Label newClient;
    @FXML
    private Button saveData;
    @FXML
    private PasswordField textPass;

    @FXML
    private TextField textUser;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtFC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSurname;

    @FXML
    private Label error;
    @FXML
    protected void Save(ActionEvent event) throws IOException, ClassNotFoundException {

        String username= textUser.getText();
        String password= textPass.getText();
        String name= txtName.getText();
        String surname= txtSurname.getText();
        String email= txtEmail.getText();
        String p=txtPhone.getText();
        String address= txtAddress.getText();
        String codicefiscale= txtFC.getText();
        if((username.isBlank())||(surname.isBlank())||(email.isBlank())||(password.isBlank())||(name.isBlank())||(p.isBlank())||(address.isBlank())||(codicefiscale.isBlank())){
            error.setVisible(true);
            return;
        }
        int phone= Integer.parseInt(p);
        Client c=new Client(name,surname,codicefiscale,email,phone,address,0,username,password);
        os.writeObject("newUser");
        os.flush();
        if (is == null)
        {
           /* is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            */
            is = new ObjectInputStream(client.getInputStream());
        }

        String o = (String) is.readObject();
        if(o.equals("OK")) {
            os.writeObject(c);
            os.flush();
            if (is == null) {
                /* is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
                */
                is = new ObjectInputStream(client.getInputStream());
            }
            String message = (String) is.readObject();
            if(message.equals("Add new client")){
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Login ONLINE WINE SHOP");
                stage.setScene(new Scene(root, 500, 400));
                stage.setResizable(false);
                stage.show();
                Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                thisStage.hide();
            }
        }
    }
}

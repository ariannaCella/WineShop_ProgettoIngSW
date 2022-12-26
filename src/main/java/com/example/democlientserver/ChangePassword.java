package com.example.democlientserver;

import Actors.Client;
import RequestResponse.RequestChangePassword;
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

public class ChangePassword {

    @FXML
    private Button home;

    @FXML
    private Label message;

    @FXML
    private Button modify;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField oldPassword;

    @FXML
    private TextField username;

    @FXML
    void modify(ActionEvent event) throws IOException, ClassNotFoundException {
        String Username= username.getText();
        String oldpassword= oldPassword.getText();
        String newpassword= newPassword.getText();

        if((Username.isBlank())||(oldpassword.isBlank())||(newpassword.isBlank())){
            message.setVisible(true);
            message.setText("Compilare tutti i campi");
            return;

        }

        os.writeObject("modifyPassw");
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
            RequestChangePassword req= new RequestChangePassword(Username,oldpassword,newpassword);
            os.writeObject(req);
            os.flush();
            if (is == null) {

            }
            String message1 = (String) is.readObject();
            if(message1.equals("Change Password")){
                message.setVisible(true);
                message.setText("Modifica avvenuta correttamente");
            }
        }
    }



    @FXML
    void returnHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeEmployee.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Home Employee");
        stage.setScene(new Scene(root, 600, 600));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void txtNewPassword(ActionEvent event) {

    }

    @FXML
    void txtOldPassword(ActionEvent event) {

    }

    @FXML
    void txtUsername(ActionEvent event) {

    }

}

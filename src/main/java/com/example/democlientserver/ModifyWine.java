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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.os;

public class ModifyWine {

    @FXML
    private Button back;

    @FXML
    private Button modify;

    @FXML
    private CheckBox okNote;

    @FXML
    private CheckBox okPrice;

    @FXML
    private CheckBox okQuantity;

    @FXML
    private CheckBox okYear;

    @FXML
    private TextField txtNote;

    @FXML
    private TextField txtPriice;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtyear;

    @FXML
    void back(ActionEvent event) throws IOException, ClassNotFoundException  {
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
    void modify(ActionEvent event) throws IOException, ClassNotFoundException  {
        String q=txtQuantity.getText();
        int quantity=0, year=null;
        double price = null;
        String y=txtyear.getText();
        String n=txtNote.getText();
        String p = txtPriice.getText();
        os.writeObject("ModifyWineById");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            if(quantity.isBlank()){
                int quantity=Integer.parseInt(q);
            }
            if(quantity.isBlank()){ quantity=null;}
            os.writeObject(txtId);
            os.flush();
            Parent rootEmployee = FXMLLoader.load(getClass().getResource("ModifyWine.fxml"));
            Stage stageEmployee = new Stage();
            stageEmployee.setTitle("Modify Wine");
            stageEmployee.setScene(new Scene(rootEmployee, 600, 401));
            stageEmployee.setResizable(false);
            stageEmployee.show();
            Stage thisStageEmployee = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisStageEmployee.hide();
        }

    }

    @FXML
    void okNote(ActionEvent event) {

    }

    @FXML
    void okPrice(ActionEvent event) {

    }

    @FXML
    void okQuantity(ActionEvent event) {

    }

    @FXML
    void okYear(ActionEvent event) {

    }

}

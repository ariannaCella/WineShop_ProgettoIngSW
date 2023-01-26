package com.example.democlientserver;

import RequestResponse.RequestModifyWine;
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
        String quantity=txtQuantity.getText();
        String year=txtyear.getText();
        String note=txtNote.getText();
        String price = txtPriice.getText();
        os.writeObject("ModifyWineById");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            RequestModifyWine reqModifyWine= new RequestModifyWine(quantity,price,note,year);
            System.out.println(reqModifyWine.getNote()+" "+ reqModifyWine.getQuantity()+" "+reqModifyWine.getYear()+" "+reqModifyWine.getPrice());
            os.writeObject(reqModifyWine);
            os.flush();
            Parent root = FXMLLoader.load(getClass().getResource("Management.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Management");
            stage.setScene(new Scene(root, 1008, 665));
            stage.setResizable(false);
            stage.show();
            Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            thisStage.hide();
        }
        /*String q=txtQuantity.getText();
        int quantity=0, year= 0;
        double price = 0;
        String y=txtyear.getText();
        String note=txtNote.getText();
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
            if(!(q.isBlank())){
                quantity=Integer.parseInt(q); //se è compilato abbiamo il valore, altrimenti è 0
            }
            if(note.isBlank()){
                note=null; //se è compilato abbiamo il valore, altrimenti è nullo
            }
            if(!(p.isBlank())){
                price=Double.parseDouble(p); //se è compilato abbiamo il valore, altrimenti è 0
            }
            if(!(y.isBlank())){
                year=Integer.parseInt(y); //se è compilato abbiamo il valore, altrimenti è 0
            }
            RequestModifyWine reqModifyWine= new RequestModifyWine(quantity,price,note,year);
            System.out.println(reqModifyWine.getNote()+" "+ reqModifyWine.getQuantity()+" "+reqModifyWine.getYear()+" "+reqModifyWine.getPrice());
            os.writeObject(reqModifyWine);
            os.flush();
            Parent root = FXMLLoader.load(getClass().getResource("Management.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Management");
            stage.setScene(new Scene(root, 1008, 665));
            stage.setResizable(false);
            stage.show();
            Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            thisStage.hide();
        }
*/
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

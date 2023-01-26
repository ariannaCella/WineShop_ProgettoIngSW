package com.example.democlientserver;

import Actors.Purchase;
import Actors.Wine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;

public class ViewProposalPurchase implements Initializable {

    @FXML
    private TableColumn<Purchase, String> address;
    @FXML
    private Button goHome;

    @FXML
    private TableColumn<Purchase,Boolean> delivered;

    @FXML
    private TableColumn<Purchase, Integer> nBottles;

    @FXML
    private TableColumn<Purchase, Integer> id;

    @FXML
    private TableColumn<Purchase, Float> price;

    @FXML
    private TableColumn<Purchase, Boolean> signature;

    @FXML
    private TableView<Purchase> tabPurchase;

    @FXML
    void goHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeClient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Homepage!");
        stage.setScene(new Scene(root, 550, 700));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }


    ArrayList<Purchase> purchases=new ArrayList<>();

    ObservableList<Purchase> obsPurchase=FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            os.writeObject("getListPurchaseClient");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            ArrayList<Purchase> purchases= (ArrayList<Purchase>) is.readObject();
            for(int i=0; i<purchases.size(); i++){
                Purchase temp= purchases.get(i);
                obsPurchase.add(temp);}


            address.setCellValueFactory(new PropertyValueFactory<Purchase,String>("address"));
            id.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("id"));
            nBottles.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("nBottles"));
            price.setCellValueFactory(new PropertyValueFactory<Purchase,Float>("price"));
            signature.setCellValueFactory(new PropertyValueFactory<Purchase,Boolean>("signature"));
            delivered.setCellValueFactory(new PropertyValueFactory<Purchase,Boolean>("accepted"));

            tabPurchase.setItems(obsPurchase);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }

}

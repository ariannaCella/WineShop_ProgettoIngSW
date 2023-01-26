package com.example.democlientserver;

import Actors.Client;
import Actors.Purchase;
import Actors.Sale;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;
import static com.example.democlientserver.ModelDBMS.listWineDBMS;

public class HomeSupplier implements Initializable {

    @FXML
    private Button LogOut;

    @FXML
    private TableColumn<Purchase, Boolean> accepted;

    @FXML
    private Button consegna;

    @FXML
    private Label error;

    @FXML
    private TableColumn<Purchase,Integer> id;

    @FXML
    private TableColumn<Purchase,Integer> idWine;

    @FXML
    private TableColumn<Purchase,Integer> nBottles;

    @FXML
    private TableColumn<Purchase,Double> price;

    @FXML
    private TableColumn<Purchase,Boolean> signature;

    @FXML
    private TableView<Purchase> tabPurchase;

    @FXML
    private TextField txtId;

    @FXML
    void LogOut(ActionEvent event) throws IOException {
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
    void consegna(ActionEvent event) {

    }
    ArrayList<Purchase> purchases=new ArrayList<>();
    ObservableList<Purchase> obsPurchase= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            os.writeObject("getListPurchase");
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

            id.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("purchaseId"));
            idWine.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("wineId"));
            nBottles.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("nBottles"));
            price.setCellValueFactory(new PropertyValueFactory<Purchase,Double>("price"));
            signature.setCellValueFactory(new PropertyValueFactory<Purchase,Boolean>("signature"));
            accepted.setCellValueFactory(new PropertyValueFactory<Purchase,Boolean>("accepted"));

            tabPurchase.setItems(obsPurchase);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }


}

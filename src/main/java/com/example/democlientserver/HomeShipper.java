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
import javafx.scene.control.*;
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
import static java.lang.Integer.parseInt;

public class HomeShipper implements Initializable {

    @FXML
    private TableColumn<Sale,String> address;

    @FXML
    private TableColumn<Sale, Date> date;

    @FXML
    private TableColumn<Sale,Boolean> delivered;

    @FXML
    private TableColumn<Sale, Integer> id;

    @FXML
    private TableColumn<Sale, Integer> idWine;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<Sale, Integer> nBottles;

    @FXML
    private Button okDelivered;

    @FXML
    private TableColumn<Sale,Double> price;

    @FXML
    private TableColumn<Sale, Boolean> signature;

    @FXML
    private TableView<Sale> tabSale;

    @FXML
    private TextField txtIdSale;
    @FXML
    private Label error;

    @FXML
    void logout(ActionEvent event) throws IOException {
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
    void okDelivered(ActionEvent event) throws IOException, ClassNotFoundException {
        String txt1=txtIdSale.getText();
        int idsale=parseInt(txt1);
        if(txt1.isBlank()){
            error.setVisible(true);
            //error.setText("Compilare campo id");
            return;
        }
        os.writeObject("AcceptIdSale");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            os.writeObject(idsale);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            String message = (String) is.readObject();
            if (message.equals("Updated")) {
                Parent root = FXMLLoader.load(getClass().getResource("HomeShipper.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Shipper");
                stage.setScene(new Scene(root, 934, 584));
                stage.setResizable(false);
                stage.show();
                Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                thisStage.hide();
            }
        }
    }

    @FXML
    void textIdSale(ActionEvent event) {

    }
    ArrayList<Sale> sales= new ArrayList<>();
    ObservableList<Sale> obsSale = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            os.writeObject("getListSalesShipper");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            ArrayList<Sale> sales= (ArrayList<Sale>) is.readObject();
            for(int i=0; i<sales.size(); i++){
                Sale temp= sales.get(i);
                obsSale.add(temp);}

            id.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("saleId"));
            address.setCellValueFactory(new PropertyValueFactory<Sale,String>("address"));
            idWine.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("wineId"));
            nBottles.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("nBottles"));
            price.setCellValueFactory(new PropertyValueFactory<Sale,Double>("price"));
            date.setCellValueFactory(new PropertyValueFactory<Sale, Date>("date"));
            signature.setCellValueFactory(new PropertyValueFactory<Sale,Boolean>("signature"));
            delivered.setCellValueFactory(new PropertyValueFactory<Sale,Boolean>("accepted"));
            tabSale.setItems(obsSale);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }
}

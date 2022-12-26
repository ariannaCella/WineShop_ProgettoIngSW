package com.example.democlientserver;

import Actors.*;
import RequestResponse.RequestSearchWineId;
import RequestResponse.RequestShop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;
import static com.example.democlientserver.ModelDBMS.*;


public class Management implements Initializable{
    @FXML
    private Label error,error1,error2;
    @FXML
    private TableColumn<Purchase,String> addressPurchase;

    @FXML
    private TableColumn<Sale,String> addressSale;

    @FXML
    private TableColumn<Client,String> addressClient;

    @FXML
    private TableColumn<Client,String> FiscalCodeClient;

    @FXML
    private TableColumn<Purchase,String> fcClientPurchase;

    @FXML
    private TableColumn<Purchase,String> fcSupplierPurch;

    @FXML
    private TableColumn<Sale,String> cfSale;

    @FXML
    private TableColumn<Sale,Boolean> acceptedSale;

    @FXML
    private TableColumn<Purchase,Boolean> consegnatoPurchase;

    @FXML
    private TableColumn<Sale, java.sql.Date> dateSale;

    @FXML
    private TableColumn<Client,String> emailClient;

    @FXML
    private TableColumn<Purchase,Integer> idPurchase;

    @FXML
    private TableColumn<Wine,Integer> idWine;

    @FXML
    private TableColumn<Purchase,Integer> idWinePurchase;

    @FXML
    private TableColumn<Sale,Integer> idWineSale;

    @FXML
    private Button modifyIdPurchase;

    @FXML
    private Button modifyIdSale;

    @FXML
    private Button modifyIdWine;

    @FXML
    private TableColumn<Wine,String> imageWine;

    @FXML
    private TableColumn<Purchase,Integer> nBottlesPurchase;

    @FXML
    private TableColumn<Sale,Integer> nBottlesSale;

    @FXML
    private TableColumn<Wine,Integer> nSalesWine;

    @FXML
    private TableColumn<Client, String> nameClient;

    @FXML
    private TableColumn<Wine,String> nameWine;

    @FXML
    private TableColumn<Wine,String> noteWine;

    @FXML
    private TableColumn<Wine,String> originWine;

    @FXML
    private Tab paneClient;

    @FXML
    private Tab panePurchase;

    @FXML
    private Tab paneSale;

    @FXML
    private Tab paneWine;

    @FXML
    private TableColumn<Client,Integer> phoneClient;

    @FXML
    private TableColumn<Purchase,Float> pricePurchase;

    @FXML
    private TableColumn<Sale,Float> priceSale;

    @FXML
    private TableColumn<Wine,Float> priceWine;

    @FXML
    private TableColumn<Wine,String> producerWine;

    @FXML
    private TableColumn<Wine,Float> qualityWine;

    @FXML
    private TableColumn<Wine,Integer> quantityWine;

    @FXML
    private Button returnHome;

    @FXML
    private TableColumn<Sale,Boolean> signSale;

    @FXML
    private TableColumn<Purchase,Boolean> signaturePurchase;

    @FXML
    private TableColumn<Client,String> surnameClient;

    @FXML
    private TableView<Client> tabClient;

    @FXML
    private TableView<Purchase> tabPurchase;

    @FXML
    private TableView<Sale> tabSale;

    @FXML
    private TableColumn<Sale,Integer> idSale;

    @FXML
    private TableView<Wine> tabWine;

    @FXML
    private TextField txtIdPurchase;

    @FXML
    private TextField txtIdSale;

    @FXML
    private TextField txtIdWine;

    @FXML
    private TableColumn<Client,String> usernameClient;

    @FXML
    private TableColumn<Wine,String> vinesWine;

    @FXML
    private TableColumn<Wine,Integer> yearWine;

    @FXML
    void modifyIdPurchase(ActionEvent event) {

    }

    @FXML
    void modifyIdSale(ActionEvent event) {

    }

    @FXML
    protected void modifyIdWine(ActionEvent event) throws IOException, ClassNotFoundException {
        os.writeObject("modifyWine");
        os.flush();
        String txtId=txtIdWine.getText();
        if(txtId.isBlank()){}
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            os.writeObject(txtId);
            os.flush();
        }

    }

    @FXML
    protected void goHome(ActionEvent event) throws IOException {
        Parent rootEmployee = FXMLLoader.load(getClass().getResource("HomeEmployee.fxml"));
        Stage stageEmployee = new Stage();
        stageEmployee.setTitle("Home Employee");
        stageEmployee.setScene(new Scene(rootEmployee, 600, 400));
        stageEmployee.setResizable(false);
        stageEmployee.show();
        Stage thisStageEmployee = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStageEmployee.hide();

    }

    @FXML
    void textIdPurchase(ActionEvent event) {

    }

    @FXML
    void textIdSale(ActionEvent event) {

    }

    @FXML
    void textIdWine(ActionEvent event) throws IOException, ClassNotFoundException {
            String txt=txtIdWine.getText();
            int id=Integer.parseInt(txt);
            if(txt.isBlank()){
                error.setVisible(true);
                error.setText("Inserire l'id del vino che vuoi ricercare");
                return;
            }

            os.writeObject("searchWineId");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            String o = (String) is.readObject();
            if(o.equals("OK")) {
                RequestSearchWineId reqSerch1 = new RequestSearchWineId(id);
                os.writeObject(reqSerch1);
                os.flush();
                if (is == null) {
                    is = new ObjectInputStream(new BufferedInputStream(
                            client.getInputStream()));
                }
                String message = (String) is.readObject();
                if (message.equals("View wines")) {

                }
            }
    }
    ArrayList<Sale> sales= new ArrayList<>();
    ArrayList<Client> clients=new ArrayList<>();
    ArrayList<Purchase> purchases=new ArrayList<>();
    ArrayList<Wine> wines=new ArrayList<>();

    ObservableList<Sale> obsSale = FXCollections.observableArrayList();
    ObservableList<Client> obsClient = FXCollections.observableArrayList();
    ObservableList<Purchase> obsPurchase=FXCollections.observableArrayList();
    ObservableList<Wine> obsWine=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            os.writeObject("getListSales");
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

            idSale.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("saleId"));
            cfSale.setCellValueFactory(new PropertyValueFactory<Sale,String>("fiscalCode"));
            addressSale.setCellValueFactory(new PropertyValueFactory<Sale,String>("address"));
            idWineSale.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("wineId"));
            nBottlesSale.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("nBottles"));
            priceSale.setCellValueFactory(new PropertyValueFactory<Sale,Float>("price"));
            dateSale.setCellValueFactory(new PropertyValueFactory<Sale, Date>("date"));
            signSale.setCellValueFactory(new PropertyValueFactory<Sale,Boolean>("signature"));
            acceptedSale.setCellValueFactory(new PropertyValueFactory<Sale,Boolean>("accepted"));
            tabSale.setItems(obsSale);

            os.writeObject("getListClient");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            ArrayList<Client> clients= (ArrayList<Client>) is.readObject();
            for(int i=0; i<clients.size(); i++){
                Client temp= clients.get(i);
                obsClient.add(temp);}

            nameClient.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
            surnameClient.setCellValueFactory(new PropertyValueFactory<Client,String>("surname"));
            FiscalCodeClient.setCellValueFactory(new PropertyValueFactory<Client,String>("fiscalCode"));
            emailClient.setCellValueFactory(new  PropertyValueFactory<Client,String>("email"));
            phoneClient.setCellValueFactory(new PropertyValueFactory<Client,Integer>("phone"));
            addressClient.setCellValueFactory(new PropertyValueFactory<Client,String>("address"));
            usernameClient.setCellValueFactory(new PropertyValueFactory<Client,String>("username"));
            tabClient.setItems(obsClient);

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

            idPurchase.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("purchaseId"));
            fcSupplierPurch.setCellValueFactory(new PropertyValueFactory<Purchase,String>("fiscalCode"));
            fcClientPurchase.setCellValueFactory(new PropertyValueFactory<Purchase,String>("fiscClient"));
            addressPurchase.setCellValueFactory(new PropertyValueFactory<Purchase,String>("address"));
            idWinePurchase.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("wineId"));
            nBottlesPurchase.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("nBottles"));
            pricePurchase.setCellValueFactory(new PropertyValueFactory<Purchase,Float>("price"));
            signaturePurchase.setCellValueFactory(new PropertyValueFactory<Purchase,Boolean>("signature"));
            consegnatoPurchase.setCellValueFactory(new PropertyValueFactory<Purchase,Boolean>("accepted"));

            tabPurchase.setItems(obsPurchase);


            wines=listWineDBMS();
            for(int i=0; i<wines.size(); i++){
                Wine temp= wines.get(i);
                obsWine.add(temp);}

            idWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("wineId"));
            nameWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("name"));
            producerWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("producer"));
            originWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("origin"));
            noteWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("notes"));
            yearWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("year"));
            nSalesWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("nSales"));
            quantityWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("quantity"));
            qualityWine.setCellValueFactory(new PropertyValueFactory<Wine,Float>("quality"));
            priceWine.setCellValueFactory(new PropertyValueFactory<Wine,Float>("price"));
            imageWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("img"));

            tabWine.setItems(obsWine);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }
}
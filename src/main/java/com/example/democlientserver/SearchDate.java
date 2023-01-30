package com.example.democlientserver;

import Actors.Purchase;
import Actors.Sale;
import RequestResponse.RequestChangePassword;
import RequestResponse.RequestDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;

public class SearchDate implements Initializable {


    @FXML
    private TableColumn<Purchase,Boolean> acceptedPurchase;

    @FXML
    private TableColumn<Purchase,String> addressPurchase;

    @FXML
    private TableColumn<Purchase,String> cfClient;

    @FXML
    private TableColumn<Purchase,String> cfPurchase;

    @FXML
    private DatePicker dataFine;

    @FXML
    private DatePicker dataInizio;

    @FXML
    private TableColumn<Purchase,Date> datePurchase;

    @FXML
    private Button goHome;

    @FXML
    private TableColumn<Purchase,Integer> idPurchase;

    @FXML
    private TableColumn<Purchase,Integer> idWinePurchase;

    @FXML
    private TableColumn<Purchase,Integer> nBottlesPurchase;

    @FXML
    private TableColumn<Purchase,Float> pricePurchase;

    @FXML
    private Button searchData;

    @FXML
    private TableColumn<Purchase,Boolean> signPurchase;

    @FXML
    private TableView<Purchase> tabPurchase;
    java.sql.Date dataEnd;
    java.sql.Date dataBegin;

    @FXML
    void dataFine(ActionEvent event) {
        dataEnd= java.sql.Date.valueOf(dataFine.getValue());
    }

    @FXML
    void dataInizio(ActionEvent event) {
        dataBegin= java.sql.Date.valueOf(dataInizio.getValue());
    }
    static int valore=0;
    @FXML
    void goHome(ActionEvent event) throws IOException, ClassNotFoundException {
        os.writeObject("isAdministrator");
        os.flush();
        int admin= (int) is.readObject();
        if(admin==0) {
            Parent rootEmployee = FXMLLoader.load(getClass().getResource("HomeEmployee.fxml"));
            Stage stageEmployee = new Stage();
            stageEmployee.setTitle("Home Employee");
            stageEmployee.setScene(new Scene(rootEmployee, 600, 400));
            stageEmployee.setResizable(false);
            stageEmployee.show();
            Stage thisStageEmployee = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisStageEmployee.hide();
        }
        else if(admin==1){
            Parent rootEmployee = FXMLLoader.load(getClass().getResource("HomeAdministrator.fxml"));
            Stage stageEmployee = new Stage();
            stageEmployee.setTitle("Home Administrator");
            stageEmployee.setScene(new Scene(rootEmployee, 800, 647));
            stageEmployee.setResizable(false);
            stageEmployee.show();
            Stage thisStageEmployee = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisStageEmployee.hide();
        }
    }
    ObservableList<Purchase> obsPurchase = FXCollections.observableArrayList();
    static ArrayList<Purchase> purchases=new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (valore == 1) {
            for (int i = 0; i < purchases.size(); i++) {
                Purchase temp = purchases.get(i);
                obsPurchase.add(temp);
            }

            idPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("purchaseId"));
            cfPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, String>("fiscalCode"));
            cfClient.setCellValueFactory(new PropertyValueFactory<Purchase,String>("fiscClient"));
            addressPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, String>("address"));
            idWinePurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("wineId"));
            nBottlesPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("bottles"));
            pricePurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Float>("price"));
            datePurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("data"));
            signPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Boolean>("signature"));
            acceptedPurchase.setCellValueFactory(new PropertyValueFactory<Purchase, Boolean>("accepted"));
            tabPurchase.setItems(obsPurchase);
        }
    }
    @FXML
    void searchData(ActionEvent event) throws IOException, ClassNotFoundException {
        java.sql.Date d1= dataBegin;
        java.sql.Date d2= dataEnd;
        RequestDate rd= new RequestDate(d1,d2);

        os.writeObject("SearchDate");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(client.getInputStream());
        }

        String o = (String) is.readObject();
        if(o.equals("OK")) {
            os.writeObject(rd);
            os.flush();
            purchases= (ArrayList<Purchase>) is.readObject();
            valore=1;
            Parent root = FXMLLoader.load(getClass().getResource("SearchDate.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Search Date");
            stage.setScene(new Scene(root, 1054, 550));
            stage.setResizable(false);
            stage.show();
            Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            thisStage.hide();

        }
    }

}

package com.example.democlientserver;

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
    private TableColumn<Sale,Boolean> acceptedSale;

    @FXML
    private TableColumn<Sale,String> addressSale;

    @FXML
    private TableColumn<Sale,String> cfSale;

    @FXML
    private DatePicker dataFine;

    @FXML
    private DatePicker dataInizio;

    @FXML
    private TableColumn<Sale, java.sql.Date> dateSale;

    @FXML
    private Button goHome;

    @FXML
    private TableColumn<Sale,Integer> idSale;

    @FXML
    private TableColumn<Sale,Integer> idWineSale;

    @FXML
    private TableColumn<Sale,Integer> nBottlesSale;

    @FXML
    private TableColumn<Sale,Float> priceSale;

    @FXML
    private Button searchData;

    @FXML
    private TableColumn<Sale,Boolean> signSale;

    @FXML
    private TableView<Sale> tabSale;
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
    //ciao prova
    @FXML
    void goHome(ActionEvent event) throws IOException {
        Parent rootEmployee = FXMLLoader.load(getClass().getResource("HomeEmployee.fxml"));
        Stage stageEmployee = new Stage();
        stageEmployee.setTitle("Home Employee");
        stageEmployee.setScene(new Scene(rootEmployee, 600, 400));
        stageEmployee.setResizable(false);
        stageEmployee.show();
        Stage thisStageEmployee = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStageEmployee.hide();
    }
    ObservableList<Sale> obsSale = FXCollections.observableArrayList();
    static ArrayList<Sale> salesDate=new ArrayList<>();

    @FXML
    void searchData(ActionEvent event) throws IOException, ClassNotFoundException {

        java.sql.Date d1= dataBegin;
        java.sql.Date d2= dataEnd;
        RequestDate rd= new RequestDate(d1,d2);

        os.writeObject("SearchDate");
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
            os.writeObject(rd);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(client.getInputStream());
            }
            salesDate= (ArrayList<Sale>) is.readObject();
            for (int i = 0; i < salesDate.size(); i++) {
                Sale temp = salesDate.get(i);
                System.out.println(temp.infoSale());
                obsSale.add(temp);
            }

            idSale.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("saleId"));
            cfSale.setCellValueFactory(new PropertyValueFactory<Sale, String>("fiscalCode"));
            addressSale.setCellValueFactory(new PropertyValueFactory<Sale, String>("address"));
            idWineSale.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("wineId"));
            nBottlesSale.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("nBottles"));
            priceSale.setCellValueFactory(new PropertyValueFactory<Sale, Float>("price"));
            dateSale.setCellValueFactory(new PropertyValueFactory<Sale, java.sql.Date>("d"));
            signSale.setCellValueFactory(new PropertyValueFactory<Sale, Boolean>("signature"));
            acceptedSale.setCellValueFactory(new PropertyValueFactory<Sale, Boolean>("accepted"));
            tabSale.setItems(obsSale);

            //initialize();

        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*if (valore == 1) {
            for (int i = 0; i < salesDate.size(); i++) {
                Sale temp = salesDate.get(i);
                obsSale.add(temp);
            }

            idSale.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("saleId"));
            cfSale.setCellValueFactory(new PropertyValueFactory<Sale, String>("fiscalCode"));
            addressSale.setCellValueFactory(new PropertyValueFactory<Sale, String>("address"));
            idWineSale.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("wineId"));
            nBottlesSale.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("nBottles"));
            priceSale.setCellValueFactory(new PropertyValueFactory<Sale, Float>("price"));
            dateSale.setCellValueFactory(new PropertyValueFactory<Sale, java.sql.Date>("d"));
            signSale.setCellValueFactory(new PropertyValueFactory<Sale, Boolean>("signature"));
            acceptedSale.setCellValueFactory(new PropertyValueFactory<Sale, Boolean>("accepted"));
            tabSale.setItems(obsSale);
        }*/
    }

}






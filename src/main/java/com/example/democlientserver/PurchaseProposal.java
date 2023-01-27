package com.example.democlientserver;

import Actors.Client;
import Actors.Purchase;
import Actors.Sale;
import Actors.Wine;
import RequestResponse.RequestProposalPurchase;
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

public class PurchaseProposal implements Initializable {

    @FXML
    private TextField address, id;

    @FXML
    private CheckBox casse12;

    @FXML
    private CheckBox casse6;
    @FXML
    private Label error;

    @FXML
    private TableColumn<Wine,Integer> idWine;

    @FXML
    private TableView<Wine> listWine;

    @FXML
    private TableColumn<Wine, String> name;

    @FXML
    private TextField number;

    @FXML
    private Button sendProposal;
    @FXML
    private TableColumn<Wine,Double> price;

    @FXML
    private TextField txtUser;

    @FXML
    private TableColumn<Wine,Integer> year;
    @FXML
    protected void goHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeClient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Homepage!");
        stage.setScene(new Scene(root, 550, 700));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();

    }

    @FXML
    void makeProposal(ActionEvent event){
        try {
            String username=txtUser.getText();
            int idWine=Integer.parseInt(id.getText());
            String ad=address.getText();
            int num= Integer.parseInt(number.getText());
            if (casse6.isSelected()){
                num=num*6;
            }
            if (casse12.isSelected()){
                num=num*12;
            }
            if (casse6.isSelected() & casse12.isSelected()) {
                error.setVisible(true);
                return;
            }
            os.writeObject("Create Proposal Purchase");
            os.flush();
            RequestProposalPurchase requestProp= new RequestProposalPurchase(username,idWine,num,ad);
            os.writeObject(requestProp);
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    ArrayList<Wine> wines=new ArrayList<>();

    ObservableList<Wine> obsWine=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            os.writeObject("getListWineTot");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }

            ArrayList<Wine> wines= (ArrayList<Wine>) is.readObject();

            for(int i=0; i<wines.size(); i++){
                Wine temp= wines.get(i);
                obsWine.add(temp);}

            idWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("wineId"));
            name.setCellValueFactory(new PropertyValueFactory<Wine,String>("name"));
            year.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("year"));
            price.setCellValueFactory(new PropertyValueFactory<Wine,Double>("price"));

            listWine.setItems(obsWine);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }


}

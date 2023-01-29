package com.example.democlientserver;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;

public class ProposalPurchaseEmployee  implements Initializable {

    @FXML
    private Label error;

    @FXML
    private TextField id;


    @FXML
    private TextField number;

    @FXML
    private TableColumn<Wine, Integer> quantity;

    @FXML
    private Button sendProposal;


    @FXML
    private TableColumn<Wine,Integer> idWine;

    @FXML
    private TableView<Wine> listWine;

    @FXML
    private TableColumn<Wine, String> name;

    @FXML
    private TableColumn<Wine,Double> price;

    @FXML
    private TableColumn<Wine,Integer> year;
    @FXML
    protected void goHome(ActionEvent event) throws IOException, ClassNotFoundException {
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

    @FXML
    void makeProposal(ActionEvent event){
        try {
            if(id.getText().isBlank()||number.getText().isBlank()) {
                error.setText("Inserire valori mancanti");
                error.setVisible(true);
                return;
            }
            int idWine=Integer.parseInt(id.getText());
            int num= Integer.parseInt(number.getText());

            os.writeObject("Create Proposal Purchase Employee");
            os.flush();
            RequestProposalPurchase requestProp= new RequestProposalPurchase(idWine,num,null,0);
            os.writeObject(requestProp);
            os.flush();
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    ArrayList<Wine> wines=new ArrayList<>();

    ObservableList<Wine> obsWine= FXCollections.observableArrayList();
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
            quantity.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("quantity"));

            listWine.setItems(obsWine);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }


}

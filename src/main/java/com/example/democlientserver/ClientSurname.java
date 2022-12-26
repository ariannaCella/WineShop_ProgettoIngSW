package com.example.democlientserver;

import Actors.Client;
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

public class ClientSurname implements Initializable {

    @FXML
    private TableColumn<Client,String> FiscalCodeClient;

    @FXML
    private TableColumn<Client,String> addressClient;

    @FXML
    private TableColumn<Client,String> emailClient;

    @FXML
    private Button goHome;

    @FXML
    private TableColumn<Client,String> nameClient;

    @FXML
    private TableColumn<Client,Integer> phoneClient;

    @FXML
    private TableColumn<Client,String> surnameClient;

    @FXML
    private TableView<Client> tabClient;

    @FXML
    private TableColumn<Client,String> usernameClient;


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
    ObservableList<Client> obsClient = FXCollections.observableArrayList();
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            os.writeObject("getListSurnameClient");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            ArrayList<Client> clients = (ArrayList<Client>) is.readObject();

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

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}

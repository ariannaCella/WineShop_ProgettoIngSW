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
import javafx.scene.control.CheckBox;
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

public class PurchaseProposal implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private CheckBox casse;

    @FXML
    private TableColumn<Wine,Integer> idWine;

    @FXML
    private TableView<Wine> listWine;

    @FXML
    private TableColumn<Wine, String> name;

    @FXML
    private TextField number;

    @FXML
    private Button sandProposal;
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
    void makeProposal(ActionEvent event) {

    }
    ArrayList<Wine> wines=new ArrayList<>();

    ObservableList<Wine> obsWine=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            os.writeObject("searchWineEmployee");
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

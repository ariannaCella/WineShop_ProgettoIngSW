package com.example.democlientserver;
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
import static com.example.democlientserver.ModelDBMS.listWineDBMS;

public class wineView implements Initializable {

    @FXML
    private TableColumn<Wine, Integer> idWine;

    @FXML
    private TableColumn<Wine, String> imageWine;

    @FXML
    private TableColumn<Wine, Integer> nSalesWine;

    @FXML
    private TableColumn<Wine, String> nameWine;

    @FXML
    private TableColumn<Wine, String> noteWine;

    @FXML
    private TableColumn<Wine, String> originWine;

    @FXML
    private TableColumn<Wine, Float> priceWine;

    @FXML
    private TableColumn<Wine, String> producerWine;

    @FXML
    private TableColumn<Wine, Float> qualityWine;

    @FXML
    private TableColumn<Wine, Integer> quantityWine;

    @FXML
    private Button returnHome;

    @FXML
    private TableView<Wine> tabWine;

    @FXML
    private TableColumn<Wine, String> vinesWine;

    @FXML
    private TableColumn<Wine, Integer> yearWine;

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

    ArrayList<Wine> wines=new ArrayList<>();
    ObservableList<Wine> obsWine= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            os.writeObject("getListWinesEmployee");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            wines = (ArrayList<Wine>) is.readObject();

            for(int i = 0; i<wines.size(); i++){
                Wine temp= wines.get(i);
                obsWine.add(temp);}

            idWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("wineId"));
            nameWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("name"));
            producerWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("producer"));
            originWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("origin"));
            noteWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("notes"));
            yearWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("year"));
            nSalesWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("sales"));
            quantityWine.setCellValueFactory(new PropertyValueFactory<Wine,Integer>("quantity"));
            qualityWine.setCellValueFactory(new PropertyValueFactory<Wine,Float>("quality"));
            priceWine.setCellValueFactory(new PropertyValueFactory<Wine,Float>("price"));
            vinesWine.setCellValueFactory(new PropertyValueFactory<Wine,String>("vines"));

            tabWine.setItems(obsWine);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }



}

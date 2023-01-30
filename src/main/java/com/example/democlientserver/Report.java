package com.example.democlientserver;
import RequestResponse.ResponseReport;
import Actors.WineSold;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.ResourceBundle;

import static com.example.democlientserver.HelloApplication.*;

public class Report implements Initializable {

    @FXML
    private Label introiti;

    @FXML
    private Label nDisponibili;

    @FXML
    private Label nVendute;

    @FXML
    private Label spese;

    @FXML
    private Label valutazione;

    @FXML
    private Label venditeVino;
    @FXML
    private TableColumn<WineSold, Integer> Sold;

    @FXML
    private TableColumn<WineSold, Integer> idWine;
    @FXML
    private TableView<WineSold> tabWineSold;
    ArrayList<WineSold> winesSold=new ArrayList<>();

    ObservableList<WineSold> obsWinesSold = FXCollections.observableArrayList();

    public void goHome(ActionEvent event) throws IOException {
        Parent rootEmployee = FXMLLoader.load(getClass().getResource("HomeAdministrator.fxml"));
        Stage stageEmployee = new Stage();
        stageEmployee.setTitle("Home Administrator");
        stageEmployee.setScene(new Scene(rootEmployee, 800, 647));
        stageEmployee.setResizable(false);
        stageEmployee.show();
        Stage thisStageEmployee = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStageEmployee.hide();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            os.writeObject("getReport");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            ResponseReport report = (ResponseReport) is.readObject();
            introiti.setText(String.valueOf(report.getIncome()));
            spese.setText(String.valueOf(report.getExpenses()));
            nDisponibili.setText(String.valueOf(report.getnBottlesAvailable()));
            nVendute.setText(String.valueOf(report.getnBottleSold()));
            winesSold= report.getWineSold();
            for(int i=0; i<winesSold.size(); i++){
                WineSold temp= winesSold.get(i);
                obsWinesSold.add(temp);}

            idWine.setCellValueFactory(new PropertyValueFactory<WineSold,Integer>("ID"));
            Sold.setCellValueFactory(new PropertyValueFactory<WineSold,Integer>("wineSold"));

            tabWineSold.setItems(obsWinesSold);
            System.out.println("mediavalreport:"+String.valueOf(report.getAverageVote()));
            valutazione.setText(String.valueOf(report.getAverageVote()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}

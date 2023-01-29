package com.example.democlientserver;

import Actors.*;
import RequestResponse.RequestMonthYearReport;
import RequestResponse.RequestSearchWine;
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

public class HomeAdministrator implements Initializable {
    @FXML
    private Button LogOut;
    @FXML
    private Button SearchClientSurname;
    @FXML
    private TextField txtClientSurname;
    @FXML
    private TextField txtWine;
    @FXML
    private Button SearchWine;

    @FXML
    private Label SubTitle;

    @FXML
    private Label Title;

    @FXML
    private Button managDb;

    @FXML
    private Button modifyPassw;

    @FXML
    private Button searchPurchaseDate;
    @FXML
    private ChoiceBox<String> ChoiceWine;
    @FXML
    private ChoiceBox<?> ChoiceMonth;

    @FXML
    private TextField ChoiceYear;
    @FXML
    private Label error;
    @FXML
    private TableColumn<Employee,String> name;

    @FXML
    private Button newEmployee;

    @FXML
    private Button report;
    @FXML
    private TableColumn<Employee,String> surname;

    @FXML
    private TableView<Employee> tabEmployee;
    @FXML
    private TextField txtRemove;
    @FXML
    private TableColumn<Employee,String> username;
    @FXML
    void newEmployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignInEmployee.fxml"));
        Stage stage = new Stage();
        stage.setTitle("SignIn!");
        stage.setScene(new Scene(root, 335, 500));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }
    @FXML
    void removeEmployee(ActionEvent event) throws IOException, ClassNotFoundException {
        String txt1=txtRemove.getText();
        if(txt1.isBlank()){
            error.setVisible(true);
            error.setText("Compilare campo username");
            return;
        }
        os.writeObject("removeEmployee");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            os.writeObject(txt1);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            String message = (String) is.readObject();
            if (message.equals("Removed")) {
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
    }

    @FXML
    void report(ActionEvent event) throws IOException {
        os.writeObject("SaveReport");
        os.flush();
        int year=Integer.parseInt(ChoiceYear.getText());
        int month= Integer.parseInt((String) ChoiceMonth.getValue());
        RequestMonthYearReport monthYear=new RequestMonthYearReport (year,month);
        os.writeObject(monthYear);
        os.flush();
        Parent root = FXMLLoader.load(getClass().getResource("Report.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Monthly Report");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }
    @FXML
    void LogOut(ActionEvent event) throws IOException, ClassNotFoundException {
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
    void SearchWine(ActionEvent event) throws IOException, ClassNotFoundException {
        String txt=txtWine.getText();
        if(txt.isBlank()){
            error.setVisible(true);
            error.setText("Inserire anno o nome del vino che vuoi ricercare");
            return;
        }
        String attribute=ChoiceWine.getValue();
        if(attribute==null){
            error.setVisible(true);
            error.setText("Scegli la ricerca per anno o per nome");
            return;
        }

        os.writeObject("searchWineEmployee");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            RequestSearchWine reqSerch = new RequestSearchWine(txt, attribute);
            os.writeObject(reqSerch);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }

            Parent root = FXMLLoader.load(getClass().getResource("wineView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("wines!");
            stage.setScene(new Scene(root, 1000, 800));
            stage.setResizable(false);
            stage.show();
            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisStage.hide();

        }

    }

    @FXML
    void searchClientSurname(ActionEvent event) throws IOException, ClassNotFoundException {
        String txt1=txtClientSurname.getText();
        if(txt1.isBlank()){
            error.setVisible(true);
            error.setText("Compilare campo cognome");
            return;
        }
        os.writeObject("searchClientSurname");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            os.writeObject(txt1);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            String message = (String) is.readObject();
            if (message.equals("View Clients")) {
                Parent root = FXMLLoader.load(getClass().getResource("ClientSurname.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Clients");
                stage.setScene(new Scene(root, 1050, 800));
                stage.setResizable(false);
                stage.show();
                Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisStage.hide();
            }
        }
    }

    @FXML
    void managementDatabase(ActionEvent event) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("Management.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Management");
        stage.setScene(new Scene(root, 1008, 665));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void modifyPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Change Password");
        stage.setScene(new Scene(root, 612, 470));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void searchPurchaseForDates(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SearchDate.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Search Date");
        stage.setScene(new Scene(root, 1054, 550));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void textWineName(ActionEvent event) {

    }

    @FXML
    void textWineYear(ActionEvent event) {

    }
    ArrayList<Employee> employees=new ArrayList<>();
    ObservableList<Employee> obsEmployee = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            os.writeObject("getListEmployee");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }

            ArrayList<Employee> employees;
            employees= (ArrayList<Employee>) is.readObject();

            for(int i=0; i<employees.size(); i++){
                Employee temp= employees.get(i);
                obsEmployee.add(temp);}

            name.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
            surname.setCellValueFactory(new PropertyValueFactory<Employee,String>("surname"));
            username.setCellValueFactory(new PropertyValueFactory<Employee,String>("username"));
            tabEmployee.setItems(obsEmployee);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

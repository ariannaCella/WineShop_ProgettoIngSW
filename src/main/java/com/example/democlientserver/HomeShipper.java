package com.example.democlientserver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HomeShipper {

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> delivered;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> idWine;

    @FXML
    private TableColumn<?, ?> nBottles;

    @FXML
    private Button okDelivered;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> signature;

    @FXML
    private TableView<?> tabSale;

    @FXML
    private TextField txtIdSale;

    @FXML
    void okDelivered(ActionEvent event) {

    }

    @FXML
    void textIdSale(ActionEvent event) {

    }

}

package com.example.democlientserver;

import Actors.Wine;
import RequestResponse.ResponseOrderRepilog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;

public class OrderInvoice implements Initializable {

    @FXML
    private Label infoAddress;

    @FXML
    private Label infoClient;

    @FXML
    private Label infoDate;

    @FXML
    private Label infoWine;

    @FXML
    void GoHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeClient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Homepage!");
        stage.setScene(new Scene(root, 550, 700));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            os.writeObject("viewOrder");
            os.flush();

            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            ResponseOrderRepilog res=(ResponseOrderRepilog) is.readObject();
            infoClient.setText(res.getClient().getName()+" "+ res.getClient().getSurname());
            infoWine.setText(res.getWine().getName()+": prezzo di vendita alla bottiglia:"+res.getWine().getPrice()+"$\nN° di bottiglie acquistate "+res.getBottle()+"\nPrezzo totale(con eventuali promozioni dovute all'acquisto di casse):"+(float)res.getPrice()+"$");
            infoAddress.setText(res.getClient().getAddress());
            String d=Integer.toString(res.getDate());
            infoDate.setText(d);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}

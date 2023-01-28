package com.example.democlientserver;
import RequestResponse.ResponseReport;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}

package com.example.democlientserver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.democlientserver.HelloApplication.os;

public class Payment {
    @FXML
    private ChoiceBox<String> choicePayment;

    @FXML
    private Button payment;
    @FXML
    private Label error;

    @FXML
    private TextField txtPsw;

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField iban;
    @FXML
    private TextField card;

    @FXML
    void makeOrder(ActionEvent event) throws IOException {
        String txtInput=txtUsername.getText();
        String pswInput=txtPsw.getText();
        String controlPaymentData = null;
        if(iban.isVisible()){controlPaymentData=iban.getText();}
        else if(card.isVisible()){controlPaymentData=card.getText();}
        else if(choicePayment.getValue()==null){
            error.setText("Inserire metodo di pagamento");
            error.setVisible(true);
            return;
        }

        String pswUser= ModelDBMS.returnPassword(txtInput,"client");
        if((pswUser.equals("Utente inesistente"))){
            error.setText("Nome utente errato, riprova o registrati");
            error.setVisible(true);
        }
        if((pswUser.equals("errore"))){
            error.setText("Servizio momentaneamente fuori servizio! Ci scusiamo per il disagio");
            error.setVisible(true);
        }
        else {
            if (pswUser.equals(pswInput)) {
                  //genero ordine
                    System.out.println("ordine generato");
                    os.writeObject("generateOrder");
                    os.flush();
                    Parent root = FXMLLoader.load(getClass().getResource("OrderInvoice.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("myOrder!");
                    stage.setScene(new Scene(root, 734, 464));
                    stage.setResizable(false);
                    stage.show();
                    Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    thisStage.hide();
            }
            else{
                error.setText("Password sbagliata");
                error.setVisible(true);
            }
            }
    }

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

    public void dataPayment() {
        if(choicePayment.getValue().equals("Bonifico")){
            iban.setVisible(true);
            card.setVisible(false);
        }
        else{
            card.setVisible(true);
            iban.setVisible(false);
        }
    }
}

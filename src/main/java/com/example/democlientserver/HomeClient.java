package com.example.democlientserver;
import static com.example.democlientserver.HelloApplication.*;

import Actors.Wine;
import RequestResponse.RequestLogin;
import RequestResponse.RequestSearchWine;
import RequestResponse.RequestShop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeClient implements Initializable {

    @FXML
    private ChoiceBox<String> choiceTypeUser;

    @FXML
    private ChoiceBox<String> choiceTypeUser0;

    @FXML
    private ChoiceBox<String> choiceTypeUser1;

    @FXML
    private ChoiceBox<String> choiceTypeUser2;

    @FXML
    private Button goOk;
    @FXML
    private Label error,error0,error1,error2;
    @FXML
    private Label imgWine0;

    @FXML
    private Label imgWine1;

    @FXML
    private Label imgWine2;

    @FXML
    private Button listWine;

    @FXML
    private Button logout,viewProposal;

    @FXML
    private Label promo;

    @FXML
    private TextField quantityShop0;

    @FXML
    private TextField quantityShop1;

    @FXML
    private TextField quantityShop2;

    @FXML
    private TextField search;

    @FXML
    private Label txtInfoWine0;

    @FXML
    private Label txtInfoWine1;

    @FXML
    private Label txtInfoWine2;

    @FXML
    private VBox viewShop0;

    @FXML
    private VBox viewShop1;

    @FXML
    private VBox viewShop2;

    @FXML
    private Label welcomeText;

    private ArrayList <Label> txtInfoWine = new ArrayList<>();
    private ArrayList <Label> imgInfoWine = new ArrayList<>();
    //public static ArrayList <Wine> winesInPromo=new ArrayList<Wine>();
    private ArrayList <VBox> forShop = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            os.writeObject("viewPromoHome");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            ArrayList<Wine> winesPromo = (ArrayList<Wine>) is.readObject();
            txtInfoWine.add(txtInfoWine0);
            txtInfoWine.add(txtInfoWine1);
            txtInfoWine.add(txtInfoWine2);
            imgInfoWine.add(imgWine0);
            imgInfoWine.add(imgWine1);
            imgInfoWine.add(imgWine2);
            forShop.add(viewShop0);
            forShop.add(viewShop1);
            forShop.add(viewShop2);
            for(int i=0;i<winesPromo.size();i++) {
                txtInfoWine.get(i).setText(winesPromo.get(i).infoWine());
                forShop.get(i).setVisible(true);
                InputStream stream = null;
                try {
                    stream = new FileInputStream("C:\\Users\\huawei\\WineShop\\demo1\\src\\main\\resources\\graf\\demo1\\"+winesPromo.get(i).getImgWine());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Image image = new Image(stream);
                ImageView imageView = new ImageView();
                //Setting image to the image view
                imageView.setImage(image);
                imageView.setX(0);
                imageView.setY(0);
                imageView.setFitWidth(118.0);
                imageView.setFitHeight(142.0);
                imageView.setPreserveRatio(true);
                imgInfoWine.get(i).setGraphic(imageView);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void searchWine(ActionEvent event) throws IOException, ClassNotFoundException {
        String txt=search.getText();
        if(txt.isBlank()){
            error.setVisible(true);
            error.setText("Inserire anno o nome del vino che vuoi ricercare");
            return;
        }
        String attribute=choiceTypeUser.getValue();
        if(attribute==null){
            error.setVisible(true);
            error.setText("Scegli se vuoi scegliere per anno o per nome");
            return;
        }

        os.writeObject("searchWine");
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
            String message = (String) is.readObject();
            if (message.equals("View wines")) {
                Parent root = FXMLLoader.load(getClass().getResource("ListWines.fxml"));
                Stage stage = new Stage();
                stage.setTitle("wines!");
                stage.setScene(new Scene(root, 600, 700));
                stage.setResizable(false);
                stage.show();
                Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisStage.hide();
            }
        }
    }

    private void functionShop(int number,Wine chosenBottleWine,String typeCaseOrBottle,int buttonNumber,ActionEvent event) throws IOException, ClassNotFoundException {
        os.writeObject("shopWinePromo");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        String o = (String) is.readObject();
        if(o.equals("OK")) {
            RequestShop req=new RequestShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber);
            os.writeObject(req);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            String message = (String) is.readObject();
            if(message.equals("payment")){
                Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Shop");
                stage.setScene(new Scene(root, 600, 400));
                stage.setResizable(false);
                stage.show();
                Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                thisStage.hide();
            }
            else{
                //compilo proposta di acquisto
                System.out.println("ordine generato");
                Parent root = FXMLLoader.load(getClass().getResource("PurchaseProposal.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Shop");
                stage.setScene(new Scene(root, 600, 438));
                stage.setResizable(false);
                stage.show();
                Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                thisStage.hide();
            }
        }
    }
    @FXML
    protected void shopWine0(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        os.writeObject("getWinesPromo");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        ArrayList<Wine> winesPromo = (ArrayList<Wine>) is.readObject();
        int buttonNumber=0;
        Wine chosenBottleWine= winesPromo.get(0);
        String typeCaseOrBottle=choiceTypeUser0.getValue();
        String n=quantityShop0.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error0.setVisible(true);return;}
        int number=Integer.parseInt(n);
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }

    @FXML
    protected void shopWine1(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        os.writeObject("getWinesPromo");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        ArrayList<Wine> winesPromo = (ArrayList<Wine>) is.readObject();
        int buttonNumber=1;
        Wine chosenBottleWine= winesPromo.get(1);
        String typeCaseOrBottle= choiceTypeUser1.getValue();
        String n=quantityShop1.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error1.setVisible(true);return;}
        int number=Integer.parseInt(n);
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }

    @FXML
    protected void shopWine2(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        os.writeObject("getWinesPromo");
        os.flush();
        if (is == null)
        {
            is = new ObjectInputStream(new BufferedInputStream(
                    client.getInputStream()));
        }
        ArrayList<Wine> winesPromo = (ArrayList<Wine>) is.readObject();
        int buttonNumber=2;
        Wine chosenBottleWine= winesPromo.get(2);
        String typeCaseOrBottle=choiceTypeUser2.getValue();
        String n=quantityShop2.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error2.setVisible(true);return;}
        int number=Integer.parseInt(n);
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }
    @FXML
    protected void viewWineList(ActionEvent event) throws IOException, ClassNotFoundException {
        os.writeObject("getListWines");
        os.flush();
        Parent root = FXMLLoader.load(getClass().getResource("ListWines.fxml"));
        Stage stage = new Stage();
        stage.setTitle("wines!");
        stage.setScene(new Scene(root, 600, 700));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Login ONLINE WINE SHOP");
        stage.setScene(new Scene(root, 500, 400));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        thisStage.hide();
    }


    public void proposalPurchaseView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PurchaseProposal.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Login ONLINE WINE SHOP");
        stage.setScene(new Scene(root, 500, 400));
        stage.setResizable(false);
        stage.show();
        Stage thisStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        thisStage.hide();
    }
}


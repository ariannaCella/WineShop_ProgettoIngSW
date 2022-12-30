package com.example.democlientserver;

import Actors.Wine;
import RequestResponse.RequestSearchWine;
import RequestResponse.RequestShop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.democlientserver.HelloApplication.*;
import static com.example.democlientserver.HelloApplication.is;


public class ListWines implements Initializable{
    @FXML
    private ChoiceBox<String> choiceTypeUser0;

    @FXML
    private ChoiceBox<String> choiceTypeUser1;

    @FXML
    private ChoiceBox<String> choiceTypeUser2;

    @FXML
    private ChoiceBox<String> choiceTypeUser3;

    @FXML
    private ChoiceBox<String> choiceTypeUser4;
    @FXML
    private ChoiceBox<String> choiceTypeUser5;
    @FXML
    private Button goBackHome;
    @FXML
    private Label noWine;
    @FXML
    private Label error0;

    @FXML
    private Label error1;

    @FXML
    private Label error2;

    @FXML
    private Label error3;

    @FXML
    private Label error4;

    @FXML
    private Label error5;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox viewShop0;

    @FXML
    private VBox viewShop1;

    @FXML
    private VBox viewShop2;

    @FXML
    private VBox viewShop3;

    @FXML
    private VBox viewShop4;
    @FXML
    private VBox viewShop5;

    @FXML
    private Label imgWine0;

    @FXML
    private Label imgWine1;

    @FXML
    private Label imgWine2;

    @FXML
    private Label imgWine3;

    @FXML
    private Label imgWine4;
    @FXML
    private Label imgWine5;

    @FXML
    private Label txtInfoWine0;

    @FXML
    private Label txtInfoWine1;

    @FXML
    private Label txtInfoWine2;

    @FXML
    private Label txtInfoWine3;

    @FXML
    private Label txtInfoWine4;
    @FXML
    private Label txtInfoWine5;
    @FXML
    private Button shopping0,shopping1,shopping2,shopping3,shopping4,shopping5;
    @FXML
    private TextField quantityShop0,quantityShop1,quantityShop2,quantityShop3,quantityShop4,quantityShop5;
    @FXML
    private ArrayList <Label> txtInfoWine = new ArrayList<>();
    private ArrayList <Label> imgInfoWine = new ArrayList<>();
    private ArrayList <VBox> forShop = new ArrayList<>();
    private static Wine chosenBottleWine;
    private static ArrayList<Wine> winesList=new ArrayList<Wine>();

    /** {@inheritDoc} **/
    @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        try {
            os.writeObject("viewListWines");
            os.flush();
            if (is == null)
            {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            winesList = (ArrayList<Wine>) is.readObject();
            int n=winesList.size();
            txtInfoWine.add(txtInfoWine0);
            txtInfoWine.add(txtInfoWine1);
            txtInfoWine.add(txtInfoWine2);
            txtInfoWine.add(txtInfoWine3);
            txtInfoWine.add(txtInfoWine4);
            txtInfoWine.add(txtInfoWine5);
            imgInfoWine.add(imgWine0);
            imgInfoWine.add(imgWine1);
            imgInfoWine.add(imgWine2);
            imgInfoWine.add(imgWine3);
            imgInfoWine.add(imgWine4);
            imgInfoWine.add(imgWine5);
            forShop.add(viewShop0);
            forShop.add(viewShop1);
            forShop.add(viewShop2);
            forShop.add(viewShop3);
            forShop.add(viewShop4);
            forShop.add(viewShop5);
            if(n>0){
                scroll.setVisible(true);
                for(int i=0;i<n;i++) {
                    txtInfoWine.get(i).setText(winesList.get(i).infoWine());
                    forShop.get(i).setVisible(true);
                    InputStream stream = null;
                    try {
                        stream = new FileInputStream("C:\\Users\\Elena\\Desktop\\ProgettoPoggi\\ProgettoPoggi\\demoClient-server\\src\\main\\resources\\com\\example\\democlientserver\\"+winesList.get(i).getImgWine());
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
            }
            else {
                noWine.setVisible(true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    private void functionShop(int number,Wine chosenBottleWine,String typeCaseOrBottle,int buttonNumber,ActionEvent event) throws IOException, ClassNotFoundException {
        os.writeObject("shopWine");
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
        int buttonNumber=0;
        chosenBottleWine=winesList.get(0);
        String typeCaseOrBottle=choiceTypeUser0.getValue();
        String n=quantityShop0.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error0.setVisible(true);return;}
        int number=Integer.parseInt(n);
        if(number==0){error0.setVisible(true);return;}
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }

    public void shopWine1(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        int buttonNumber=1;
        chosenBottleWine=winesList.get(1);
        String typeCaseOrBottle=choiceTypeUser1.getValue();
        String n=quantityShop1.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error1.setVisible(true);return;}
        int number=Integer.parseInt(n);
        if(number==0){error0.setVisible(true);return;}
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }
    public void shopWine2(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        int buttonNumber=2;
        chosenBottleWine=winesList.get(2);
        String typeCaseOrBottle=choiceTypeUser2.getValue();
        String n=quantityShop2.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error2.setVisible(true);return;}
        int number=Integer.parseInt(n);
        if(number==0){error0.setVisible(true);return;}
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }
    public void shopWine3(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        int buttonNumber=3;
        chosenBottleWine=winesList.get(3);
        String typeCaseOrBottle=choiceTypeUser3.getValue();
        String n=quantityShop3.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error3.setVisible(true);return;}
        int number=Integer.parseInt(n);
        if(number==0){error0.setVisible(true);return;}
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }
    public void shopWine4(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        int buttonNumber=4;
        chosenBottleWine=winesList.get(4);
        String typeCaseOrBottle=choiceTypeUser4.getValue();
        String n=quantityShop4.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error4.setVisible(true);return;}
        int number=Integer.parseInt(n);
        if(number==0){error0.setVisible(true);return;}
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }
    public void shopWine5(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        int buttonNumber=5;
        chosenBottleWine=winesList.get(5);
        String typeCaseOrBottle=choiceTypeUser5.getValue();
        String n=quantityShop5.getText();
        if((n.isBlank())||typeCaseOrBottle==null){error5.setVisible(true);return;}
        int number=Integer.parseInt(n);
        if(number==0){error0.setVisible(true);return;}
        functionShop(number,chosenBottleWine,typeCaseOrBottle,buttonNumber,actionEvent);
    }

}

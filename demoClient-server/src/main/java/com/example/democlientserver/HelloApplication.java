package com.example.democlientserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloApplication extends Application {

    private static final int SPORT = 4444;
    private static final String SHOST = "localhost";
    public static Socket client;
    static {
        try {
            client = new Socket(SHOST, SPORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObjectOutputStream os;

    static {
        try {
            os = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectInputStream  is = null;



    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        os.writeObject("Vorrei connettermi"); //vogliamo scrivere al server rq
        os.flush();

        if (is == null)
            {
               /* is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
                */
                is = new ObjectInputStream(client.getInputStream());
            }

        String o = (String) is.readObject();
        if(o.equals("OK")){
                stage.show();
            }
        else{
                System.out.println("SERVIZIO NON DISPONIBILE");
            }

    }


    public static void main(String[] args) {
        launch();
    }
}
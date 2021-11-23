package cutit.cutit.logic.controllers;

import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControllerg {

    private final Stage prStage = Client.getPrStage();
    

    @FXML
    public boolean tryLogin() throws IOException {
        System.out.println("Client logged");
        BorderPane startClient = null;
        startClient = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/startclient.fxml"));
        Client.setPrLayout(startClient);
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/home.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        homeLayout.setBackground(new Background(back));
        startClient.setCenter(homeLayout);
        Scene scene = new Scene(startClient);
        prStage.setScene(scene);
        return true;
    }

    @FXML
    public boolean goSignUp() throws IOException {
        System.out.println("SignUp");
        BorderPane signUpLayout = null;
        signUpLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/signup.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        signUpLayout.setBackground(new Background(back));
        Client.setPrLayout(signUpLayout);
        Scene scene = new Scene(signUpLayout);
        prStage.setScene(scene);
        return true;
    }

    @FXML
    public boolean hairLogin() throws IOException {
        System.out.println("hairdresser logged");
        BorderPane startHairdresser = null;
        startHairdresser = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/starthairdresser.fxml"));
        Client.setPrLayout(startHairdresser);
        VBox appHairLayout = null;
        appHairLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/hairdresserappointments.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        appHairLayout.setBackground(new Background(back));
        startHairdresser.setCenter(appHairLayout);
        startHairdresser.setCenter(appHairLayout);
        Scene scene = new Scene(startHairdresser);
        prStage.setScene(scene);
        return true;
    }

}

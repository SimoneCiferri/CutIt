package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControllerg {

    private final Stage prStage = MainView.getPrStage();

    @FXML
    public boolean tryLogin() throws IOException {
        System.out.println("Client logged");
        BorderPane startClient = null;
        startClient = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/startclient.fxml"));
        MainView.setPrLayout(startClient);
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/home.fxml"));
        startClient.setCenter(homeLayout);
        Scene scene = new Scene(startClient);
        prStage.setScene(scene);
        return true;
    }

    @FXML
    public boolean goSignUp() throws IOException {
        System.out.println("SignUp");
        BorderPane signUpLayout = null;
        signUpLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/signup.fxml"));
        MainView.setPrLayout(signUpLayout);
        Scene scene = new Scene(signUpLayout);
        prStage.setScene(scene);
        return true;
    }

    @FXML
    public boolean hairLogin() throws IOException {
        System.out.println("hairdresser logged");
        BorderPane startHairdresser = null;
        startHairdresser = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/starthairdresser.fxml"));
        MainView.setPrLayout(startHairdresser);
        /*
        VBox appHairLayout = null;
        appHairLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/ ... .fxml"));
        startHairdresser.setCenter(appHairLayout);
         */
        Scene scene = new Scene(startHairdresser);
        prStage.setScene(scene);
        return true;
    }

}

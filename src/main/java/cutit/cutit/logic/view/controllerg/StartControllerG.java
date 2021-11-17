package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StartControllerG {
    private final Stage prStage = MainView.getPrStage();
    private BorderPane pLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private Label btnHome, btnPromotions;

    public boolean initialize() throws IOException {
        System.out.println("Home page ");
        btnHome.setStyle(pageFlagStyle);
        btnPromotions.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goHome() throws IOException {
        System.out.println("Home Button pressed");
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/home.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        homeLayout.setBackground(new Background(back));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(homeLayout);
        btnHome.setStyle(pageFlagStyle);
        btnPromotions.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goProm() throws IOException {
        System.out.println("Promotion Button pressed");
        VBox promLayout = null;
        promLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/unloggedpromotions.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        promLayout.setBackground(new Background(back));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(promLayout);
        btnHome.setStyle(transparentStyle);
        btnPromotions.setStyle(pageFlagStyle);
        return true;
    }

    @FXML
    public boolean goToLogin() throws IOException{
        System.out.println("Login page");
        BorderPane loginPage = null;
        loginPage = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/login.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        loginPage.setBackground(new Background(back));
        MainView.setPrLayout(loginPage);
        Scene scene = new Scene(loginPage);
        prStage.setScene(scene);
        return true;
    }

}

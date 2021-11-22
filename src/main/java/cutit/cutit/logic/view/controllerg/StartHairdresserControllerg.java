package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StartHairdresserControllerg {

    private final Stage prStage = MainView.getPrStage();
    private BorderPane pLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private Label  btnHApp, btnHPromotions, btnHServices, btnHShop,  btnHLogout;

    public boolean initialize() throws IOException {
        System.out.println("App page (Hairdresser)");
        btnHApp.setStyle(pageFlagStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goApp() throws IOException {
        System.out.println("Appointment Button pressed (Hairdresser)");
        VBox appLayout = null;
        appLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/hairdresserappointments.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        appLayout.setBackground(new Background(back));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(appLayout);
        btnHApp.setStyle(pageFlagStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goProm() throws IOException {
        System.out.println("Promotion Button pressed (Hairdresser)");
        VBox promLayout = null;
        promLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/hairdresserpromotions.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        promLayout.setBackground(new Background(back));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(promLayout);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(pageFlagStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goServices() throws IOException {
        System.out.println("Services Button pressed (Hairdresser)");
        /*
        VBox promLayout = null;
        promLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/hairdresserservices.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        promLayout.setBackground(new Background(back));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(promLayout);
         */
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(pageFlagStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }


    @FXML
    public boolean goShop() throws IOException {
        System.out.println("Shop Button pressed (Hairdresser)");
        VBox shopLayout = null;
        shopLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/hairdressershop.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        shopLayout.setBackground(new Background(back));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(shopLayout);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(pageFlagStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean tryLogout() throws IOException {
        System.out.println("Start page (logout from Hairdresser)");
        BorderPane start = null;
        start = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/start.fxml"));
        MainView.setPrLayout(start);
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/home.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        homeLayout.setBackground(new Background(back));
        start.setCenter(homeLayout);
        Scene scene = new Scene(start);
        prStage.setScene(scene);
        return true;
    }

}

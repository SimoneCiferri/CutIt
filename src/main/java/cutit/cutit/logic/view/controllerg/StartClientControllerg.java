package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StartClientControllerg {

    private final Stage prStage = MainView.getPrStage();
    private BorderPane pLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private Label btnClHome, btnClFav, btnClApp, btnClPromotion, btnClLogout;

    public boolean initialize() throws IOException {
        System.out.println("Home page (client)");
        btnClHome.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goHome() throws IOException {
        System.out.println("Home Button pressed (client)");
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/home.fxml"));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(homeLayout);
        btnClHome.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goFav() throws IOException {
        System.out.println("Favourites Button pressed (client)");

        VBox favLayout = null;
        favLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/favouritescs.fxml"));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(favLayout);
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(pageFlagStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goApp() throws IOException {
        System.out.println("Appointment Button pressed (client)");

        VBox appLayout = null;
        appLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientappointments.fxml"));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(appLayout);
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goProm() throws IOException {
        System.out.println("Promotion Button pressed (client)");
        VBox promLayout = null;
        promLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientpromotions.fxml"));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(promLayout);
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(pageFlagStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean tryLogout() throws IOException{
        System.out.println("Start page (logout from Client)");
        BorderPane start = null;
        start = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/start.fxml"));
        MainView.setPrLayout(start);
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/home.fxml"));
        start.setCenter(homeLayout);
        Scene scene = new Scene(start);
        prStage.setScene(scene);
        return true;
    }

}

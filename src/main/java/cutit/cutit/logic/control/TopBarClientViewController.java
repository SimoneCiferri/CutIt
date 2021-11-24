package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TopBarClientViewController {

    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private Label btnClHome, btnClFav, btnClApp, btnClPromotion, btnClLogout;

    @FXML
    private AnchorPane apTopBarClient;

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
    public boolean goHome() {
        /*
        System.out.println("Home Button pressed (client)");
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/home.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        homeLayout.setBackground(new Background(back));
        pLayout= Client.getPrLayout();
        pLayout.setCenter(homeLayout);
        btnClHome.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
         */
        Facade.getInstance().decorateView(ViewLayout.HOME);
        btnClHome.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goFav() {
        /*
        System.out.println("Favourites Button pressed (client)");
        VBox favLayout = null;
        favLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientfavouritescs.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        favLayout.setBackground(new Background(back));
        pLayout= Client.getPrLayout();
        pLayout.setCenter(favLayout);
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(pageFlagStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
         */
        Facade.getInstance().decorateView(ViewLayout.FAVSHOP);
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(pageFlagStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goApp() {
        /*
        System.out.println("Appointment Button pressed (client)");
        VBox appLayout = null;
        appLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientappointments.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        appLayout.setBackground(new Background(back));
        pLayout= Client.getPrLayout();
        pLayout.setCenter(appLayout);
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
         */
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        //Facade.getInstance().decorateView(ViewLayout.APPCLIENT);
        return true;
    }

    @FXML
    public boolean goProm() {
        /*
        System.out.println("Promotion Button pressed (client)");
        VBox promLayout = null;
        promLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientpromotions.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        promLayout.setBackground(new Background(back));
        pLayout= Client.getPrLayout();
        pLayout.setCenter(promLayout);
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(pageFlagStyle);
        btnClLogout.setStyle(transparentStyle);
         */
        Facade.getInstance().decorateView(ViewLayout.PROMOTIONCLIENT);
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(pageFlagStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean tryLogout() {
        /*
        System.out.println("Start page (logout from Client)");
        BorderPane start = null;
        start = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/start.fxml"));
        Client.setPrLayout(start);
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/home.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        homeLayout.setBackground(new Background(back));
        start.setCenter(homeLayout);
        Scene scene = new Scene(start);
        prStage.setScene(scene);
         */
        Facade.getInstance().decorateView(ViewLayout.HOME);
        return true;
    }

}
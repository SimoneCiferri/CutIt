package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TopBarViewController {

    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private Label btnHome, btnPromotions;

    @FXML
    private AnchorPane apStart;

    public boolean initialize() throws IOException {
        System.out.println("Home page ");
        btnHome.setStyle(pageFlagStyle);
        btnPromotions.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goHome() throws IOException {
        /*
        System.out.println("Home Button pressed");
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/home.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        homeLayout.setBackground(new Background(back));
        pLayout= Client.getPrLayout();
        pLayout.setCenter(homeLayout);
        btnHome.setStyle(pageFlagStyle);
        btnPromotions.setStyle(transparentStyle);
         */
        Facade.getInstance().decorateView(ViewLayout.HOME);
        btnHome.setStyle(pageFlagStyle);
        btnPromotions.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goProm() {
        /*
        System.out.println("Promotion Button pressed");
        VBox promLayout = null;
        promLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/unloggedpromotions.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        promLayout.setBackground(new Background(back));
        pLayout= Client.getPrLayout();
        pLayout.setCenter(promLayout);
        btnHome.setStyle(transparentStyle);
        btnPromotions.setStyle(pageFlagStyle);
         */
        Facade.getInstance().decorateView(ViewLayout.UNLOGGEDPROMOTIONS);
        btnHome.setStyle(transparentStyle);
        btnPromotions.setStyle(pageFlagStyle);
        return true;
    }

    @FXML
    public boolean goToLogin() {
        /*
        System.out.println("Login page");
        BorderPane loginPage = null;
        loginPage = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/login.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        loginPage.setBackground(new Background(back));
        Client.setPrLayout(loginPage);
        Scene scene = new Scene(loginPage);
        prStage.setScene(scene);
         */
        apStart.setVisible(false);
        Facade.getInstance().decorateView(ViewLayout.LOGIN);
        return true;
    }

}
package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StartViewControllerG {
    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";


    @FXML
    private Label btnHomeUn, btnPromUn, btnLoginOrSignup;

    @FXML
    private AnchorPane apStartUn;

    @FXML
    private BorderPane bpStart;


    public boolean initialize() throws IOException {
        System.out.println("Start page ");
        btnHomeUn.setStyle(pageFlagStyle);
        btnPromUn.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goHome(){
        Facade.getInstance().decorateView(ViewLayout.HOME);
        btnHomeUn.setStyle(pageFlagStyle);
        btnPromUn.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goProm(){
        Facade.getInstance().decorateView(ViewLayout.UNLOGGEDPROMOTIONS);
        btnHomeUn.setStyle(transparentStyle);
        btnPromUn.setStyle(pageFlagStyle);
        return true;
    }

    @FXML
    public boolean goLoginOrSign(){
        Facade.getInstance().decorateView(ViewLayout.LOGIN);
        bpStart.getChildren().remove(apStartUn);
        return true;
    }



}

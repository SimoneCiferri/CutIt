package cutit.cutit.logic.controller;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TopBarViewController {

    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-border-radius: 5; -fx-background-color: #A9A9A9; -fx-text-fill: #FFFFFF;";

    @FXML
    private Label btnHomeUn, btnPromotionsUn;

    @FXML
    private AnchorPane apStart;

    public boolean initialize() throws IOException {
        btnHomeUn.setStyle(pageFlagStyle);
        btnPromotionsUn.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goHome() {
        Facade.getInstance().decorateView(ViewLayout.HOME);
        btnHomeUn.setStyle(pageFlagStyle);
        btnPromotionsUn.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goProm() {
        Facade.getInstance().decorateView(ViewLayout.UNLOGGEDPROMOTIONS);
        btnHomeUn.setStyle(transparentStyle);
        btnPromotionsUn.setStyle(pageFlagStyle);
        return true;
    }

    @FXML
    public boolean goToLogin() {
        Facade.getInstance().getSTartView().getLoaded().clear();
        Facade.getInstance().getSTartView().getPrLayout().getChildren().remove(apStart);
        btnHomeUn.setStyle(transparentStyle);
        btnPromotionsUn.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout.LOGIN);
        return true;
    }

}

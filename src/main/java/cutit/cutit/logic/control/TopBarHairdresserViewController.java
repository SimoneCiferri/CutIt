package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class TopBarHairdresserViewController {

    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-border-radius: 5; -fx-background-color: #A9A9A9; -fx-text-fill: #FFFFFF;";

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
    public boolean goApp() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERAPPOINTMENTS);
        btnHApp.setStyle(pageFlagStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goProm() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERPROMOTIONS);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(pageFlagStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goServices() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERSERVICES);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(pageFlagStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }


    @FXML
    public boolean goShop() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERSHOP);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(pageFlagStyle);
        btnHLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean tryLogout() {
        Facade.getInstance().logout();
        return true;
    }

}

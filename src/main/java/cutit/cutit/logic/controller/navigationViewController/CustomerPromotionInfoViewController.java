package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

import java.io.IOException;

public class CustomerPromotionInfoViewController {

    public boolean initialize(){
        System.out.println("CONTROLLER GRAFICO CUSTOMERPROMOTIONINFOVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goBackToPromC(){
        Facade.getInstance().decorateView(ViewLayout.PROMOTIONCLIENT);
        return true;
    }

    @FXML
    public boolean goShopFromProm(){
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        return true;
    }

}

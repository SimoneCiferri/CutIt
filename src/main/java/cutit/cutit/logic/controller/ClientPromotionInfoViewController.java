package cutit.cutit.logic.controller;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class ClientPromotionInfoViewController {

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

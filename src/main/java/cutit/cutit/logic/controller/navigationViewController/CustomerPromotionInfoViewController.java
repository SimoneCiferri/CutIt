package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;

public class CustomerPromotionInfoViewController {

    @FXML
    private Label promotionTf;

    public boolean initialize(){
        System.out.println("CONTROLLER GRAFICO CUSTOMERPROMOTIONINFOVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goBackToPromC(){
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERPROMOTIONS);
        return true;
    }

    @FXML
    public boolean goShopFromProm(){
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        return true;
    }

    @FXML
    public boolean copyPromotion(){
        ClipboardContent clipboard = new ClipboardContent();
        String text = promotionTf.getText();
        return true;
    }

}

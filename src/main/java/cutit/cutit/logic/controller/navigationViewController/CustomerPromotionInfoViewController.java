package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;



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
        Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection text = new StringSelection(promotionTf.getText());
        clipboard.setContents(text,text);
        /*String resultText = clipboard.getString();
        System.out.println(resultText);*/
        return true;
    }

}

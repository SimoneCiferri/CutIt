package cutit.decorator.concrete_decorator2;

import cutit.decorator.Decorator2;
import cutit.decorator.ViewComponent2;
import cutit.decorator.ViewLayout2;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;

public class CustomerFavouritesShopView2 extends Decorator2 {

    public CustomerFavouritesShopView2(
            ViewComponent2 view){

        super(view);
        try {
            super.loadXML2(ViewLayout2.CUSTOMERFAVOURITESSHOP);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
        }

    }
}
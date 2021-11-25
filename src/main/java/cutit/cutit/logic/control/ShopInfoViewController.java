package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

import java.io.IOException;

public class ShopInfoViewController {

    public boolean initialize() throws IOException {
        return true;
    }


    @FXML
    public boolean bookAppointment() {
        Facade.getInstance().decorateView(ViewLayout.CLIENTBOOKAPPOINTMENT);
        return true;
    }

    @FXML
    public boolean rateShop() {
        Facade.getInstance().decorateView(ViewLayout.CLIENTRATESHOP);
        return true;
    }


}

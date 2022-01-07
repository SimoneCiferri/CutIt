package cutit.controller.bookappointment;

import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import javafx.fxml.FXML;

import java.io.IOException;

public class ShopInfoViewController {

    public boolean initialize() throws IOException {
        System.out.println("CONTROLLER GRAFICO SHOPINFOVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean bookAppointment() {
        Facade.getInstance().decorateView(ViewLayout.CLIENTBOOKAPPOINTMENT);
        return true;
    }

    @FXML
    public boolean backToHome(){
        Facade.getInstance().decorateView(ViewLayout.HOME);
        return true;
    }

    @FXML
    public boolean goToDirections(){
        Facade.getInstance().decorateView(ViewLayout.GMAPS);
        return true;
    }
}

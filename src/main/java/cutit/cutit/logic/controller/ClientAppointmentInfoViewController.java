package cutit.cutit.logic.controller;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class ClientAppointmentInfoViewController {

    @FXML
    public boolean goBackToAppList(){
        Facade.getInstance().decorateView(ViewLayout.APPCL);
        return true;
    }

}

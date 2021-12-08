package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

import java.io.IOException;

public class CustomerAppointmentInfoViewController {

    public boolean initialize() {
        System.out.println("CONTROLLER GRAFICO CUSTOMERAPPOINTMENTINFOVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goBackToAppList(){
        Facade.getInstance().decorateView(ViewLayout.APPCL);
        return true;
    }

}

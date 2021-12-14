package cutit.cutit.logic.controller.getlocationdirections;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class CustomerAppointmentInfoViewController {

    public boolean initialize() {
        System.out.println("CONTROLLER GRAFICO CUSTOMERAPPOINTMENTINFOVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goBackToAppList(){
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERAPPOINTMENTS);
        return true;
    }

}

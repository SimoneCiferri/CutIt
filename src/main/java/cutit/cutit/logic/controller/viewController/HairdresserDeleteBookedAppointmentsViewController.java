package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class HairdresserDeleteBookedAppointmentsViewController {

    @FXML
    public boolean goBackToAppH(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERAPPOINTMENTS);
        return true;
    }

}

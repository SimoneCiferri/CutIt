package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class CustomerAppointmentInfoViewController {

    @FXML
    public boolean goBackToAppList(){
        Facade.getInstance().decorateView(ViewLayout.APPCL);
        return true;
    }

}

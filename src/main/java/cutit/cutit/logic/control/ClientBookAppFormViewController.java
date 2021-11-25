package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientBookAppFormViewController {

    @FXML
    private Label labelDateTime;

    @FXML
    public boolean bookAppPay() {
        System.out.println("Book Button pressed (Book App Form)");
        return true;
    }

    @FXML
    public boolean backToBookApp() {
        Facade.getInstance().decorateView(ViewLayout.CLIENTBOOKAPPOINTMENT);
        return true;
    }


    public boolean initialize(){
        labelDateTime.setText(ClientBookAppointmentViewController.getAppDate());
        return true;
    }

}

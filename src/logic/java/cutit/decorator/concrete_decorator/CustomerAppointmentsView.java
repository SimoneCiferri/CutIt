package cutit.decorator.concrete_decorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent;
import cutit.decorator.ViewLayout;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;

public class CustomerAppointmentsView extends Decorator {

    public CustomerAppointmentsView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CUSTOMERAPPOINTMENTS);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
        }

    }

}

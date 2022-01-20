package cutit.decorator.concrete_decorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent1;
import cutit.decorator.ViewLayout1;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;

public class HairdresserAppointmentsView1 extends Decorator {

    public HairdresserAppointmentsView1(ViewComponent1 view){

        super(view);
        try {
            super.loadXML1(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
        }

    }

}
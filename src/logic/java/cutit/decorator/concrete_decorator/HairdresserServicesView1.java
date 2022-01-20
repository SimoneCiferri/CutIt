package cutit.decorator.concrete_decorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent1;
import cutit.decorator.ViewLayout1;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;

public class HairdresserServicesView1 extends Decorator {

    public HairdresserServicesView1(ViewComponent1 view){

        super(view);
        try {
            super.loadXML1(ViewLayout1.HAIRDRESSERSERVICES);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
        }

    }

}

package cutit.cutit.logic.decorator.concreteDecorator;

import cutit.cutit.logic.decorator.Decorator;
import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.factory.AlertFactory;
import cutit.cutit.logic.log.LogWriter;
import javafx.scene.control.Alert;

import java.io.IOException;

public class ClientAppointmentInfoView extends Decorator {

    public ClientAppointmentInfoView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.CLIENTAPPINFO);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
        }
    }

}

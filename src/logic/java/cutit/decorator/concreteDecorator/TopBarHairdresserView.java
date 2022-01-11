package cutit.decorator.concreteDecorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent;
import cutit.decorator.ViewLayout;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;

public class TopBarHairdresserView extends Decorator {

    public TopBarHairdresserView(ViewComponent view){

        super(view);
        try {
            super.loadXML(ViewLayout.TOPBARHAIRDRESSER);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
        }

    }

}
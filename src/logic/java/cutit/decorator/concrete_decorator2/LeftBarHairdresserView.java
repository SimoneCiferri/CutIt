package cutit.decorator.concrete_decorator2;

import cutit.decorator.Decorator2;
import cutit.decorator.ViewComponent2;
import cutit.decorator.ViewLayout2;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LeftBarHairdresserView extends Decorator2 {

    public LeftBarHairdresserView(ViewComponent2 view){

        super(view);
        try {
            super.loadXML2(ViewLayout2.LEFTBARHAIRDRESSER);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, "", "", "");
            alert.showAndWait();
            Stage stage = (Stage) Facade2.getInstance().getStartView2().getPrLayout2().getScene().getWindow();
            stage.close();
        }

    }

}

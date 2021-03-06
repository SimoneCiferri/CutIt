package cutit.decorator.decorator2.concrete_decorator2;

import cutit.decorator.decorator2.Decorator2;
import cutit.decorator.decorator2.ViewComponent2;
import cutit.decorator.decorator2.ViewLayout2;
import cutit.utils.ExceptionText;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class GetLocationDirectionsGoogleMapsView2 extends Decorator2 {

    public GetLocationDirectionsGoogleMapsView2(ViewComponent2 view){

        super(view);
        try {
            super.loadXML2(ViewLayout2.GMAPS);
        } catch (IOException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
            Stage stage = (Stage) Facade2.getInstance().getStartView2().getPrLayout2().getScene().getWindow();
            stage.close();
        }
    }

}

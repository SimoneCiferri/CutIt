package cutit.decorator.decorator2.concrete_decorator2;

import cutit.decorator.decorator2.Decorator2;
import cutit.decorator.decorator2.ViewComponent2;
import cutit.decorator.decorator2.ViewLayout2;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class HairdresserManagePromotionsView2 extends Decorator2 {

    private static final String ERROR_TITLE = "Error";
    private static final String IO_ERROR_MESSAGE = "Impossible to load some files. If problem persists try again later or contact us at cutitapp@support.com";

    public HairdresserManagePromotionsView2(ViewComponent2 view){

        super(view);
        try {
            super.loadXML2(ViewLayout2.HAIRDRESSERMANAGEPROMOTIONS);
        } catch (IOException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ERROR_TITLE, IO_ERROR_MESSAGE);
            alert.showAndWait();
            Stage stage = (Stage) Facade2.getInstance().getStartView2().getPrLayout2().getScene().getWindow();
            stage.close();
        }

    }
}

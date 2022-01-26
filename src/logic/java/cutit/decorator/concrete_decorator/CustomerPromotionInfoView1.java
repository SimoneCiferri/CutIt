package cutit.decorator.concrete_decorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent1;
import cutit.decorator.ViewLayout1;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerPromotionInfoView1 extends Decorator {

    private static final String ERROR_TITLE = "Error";
    private static final String IO_ERROR_MESSAGE = "Impossible to load some files. If problem persists try again later or contact us at cutitapp@support.com";

    public CustomerPromotionInfoView1(ViewComponent1 view){

        super(view);
        try {
            super.loadXML1(ViewLayout1.CUSTOMERPROMOTIONINFO);
        } catch (IOException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ERROR_TITLE, IO_ERROR_MESSAGE);
            alert.showAndWait();
            Stage stage = (Stage) Facade.getInstance().getStartView().getPrLayout1().getScene().getWindow();
            stage.close();
        }

    }

}

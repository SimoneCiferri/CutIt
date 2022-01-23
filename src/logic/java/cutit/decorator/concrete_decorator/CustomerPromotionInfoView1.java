package cutit.decorator.concrete_decorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent1;
import cutit.decorator.ViewLayout1;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CustomerPromotionInfoView1 extends Decorator {

    public CustomerPromotionInfoView1(ViewComponent1 view){

        super(view);
        try {
            super.loadXML1(ViewLayout1.CUSTOMERPROMOTIONINFO);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, "Error", e.getMessage(), "");
            alert.showAndWait();
            Stage stage = (Stage) Facade.getInstance().getStartView().getPrLayout1().getScene().getWindow();
            stage.close();
        }

    }

}

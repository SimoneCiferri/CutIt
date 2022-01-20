package cutit.decorator.concrete_decorator;

import cutit.decorator.Decorator;
import cutit.decorator.ViewComponent1;
import cutit.decorator.ViewLayout1;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;


public class CustomerGetLocationDirectionsView1 extends Decorator {

 public CustomerGetLocationDirectionsView1(ViewComponent1 view) {
     super(view);
     try {
         super.loadXML1(ViewLayout1.GMAPS);

     } catch (Exception e) {
         LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
         AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
     }
 }
}

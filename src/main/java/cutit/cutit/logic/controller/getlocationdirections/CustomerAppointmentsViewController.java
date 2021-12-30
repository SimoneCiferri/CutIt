package cutit.cutit.logic.controller.getlocationdirections;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomerAppointmentsViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private CustomerBean customerBean;

    @FXML
    private VBox vbInScrollCA;

    public boolean initialize() throws IOException {
        vbInScrollCA.setSpacing(15);
        System.out.println("CONTROLLER GRAFICO CUSTOMERAPPOINTMENTSVIEWCONTROLLER");
        return true;
    }

    private void showClientApp() {
        //get di tutti gli appuntamenti dal db
        for(int i = 0; i<customerBean.getAllBookedAppointments().size(); i++){
            String appDate = customerBean.getAllBookedAppointments().get(i);
            Label l = JavaFXNodeFactory.getInstance().createCardLabel(appDate, labelStyle);
            l.setOnMouseClicked((MouseEvent) -> Facade.getInstance().decorateView(ViewLayout.CLIENTAPPINFO));
            vbInScrollCA.getChildren().add(l);
        }
    }

    public void fillView(CustomerBean customerBean){
        this.customerBean = customerBean;
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
        showClientApp();
    }

}

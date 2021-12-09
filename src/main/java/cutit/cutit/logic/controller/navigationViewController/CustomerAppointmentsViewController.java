package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomerAppointmentsViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private ShopBean shopBean;

    @FXML
    private VBox vbInScrollCA;

    public boolean initialize() throws IOException {
        vbInScrollCA.setSpacing(15);
        showClientApp();
        System.out.println("CONTROLLER GRAFICO CUSTOMERAPPOINTMENTSVIEWCONTROLLER");
        return true;
    }

    private void showClientApp() {
        for(Integer i=0; i<2; i++){
            Label l = new Label("Appointment"+i.toString());
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> {
                Facade.getInstance().decorateView(ViewLayout.CLIENTAPPINFO);
            });
            vbInScrollCA.getChildren().add(l);
        }
    }

    public void fillView(ShopBean bean){
        shopBean = bean;
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}

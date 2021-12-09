package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomerPromotionsViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private ShopBean shopBean;

    @FXML
    private VBox vbInScrollCProm;

    public boolean initialize() throws IOException {
        vbInScrollCProm.setSpacing(15);
        showClientProm();
        System.out.println("CONTROLLER GRAFICO CUSTOMERPROMOTIONSVIEWCONTROLLER");
        return true;
    }

    private void showClientProm() {
        for(int i = 0; i<3; i++){
            Label l = new Label("Promotion"+ i);
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> Facade.getInstance().decorateView(ViewLayout.CLIENTPROMOTIONINFO));
            vbInScrollCProm.getChildren().add(l);
        }
        Button add = new Button("Bring Friend");
        vbInScrollCProm.getChildren().add(add);
    }

    public void fillView(ShopBean bean){
        shopBean = bean;
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }


}

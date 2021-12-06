package cutit.cutit.logic.controller.navigationViewController;

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

    @FXML
    private VBox vbInScrollCProm;

    public boolean initialize() throws IOException {
        vbInScrollCProm.setSpacing(15);
        showClientProm();
        return true;
    }

    private void showClientProm() {
        for(Integer i=0; i<3; i++){
            Label l = new Label("Promotion"+i.toString());
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> {
                Facade.getInstance().decorateView(ViewLayout.CLIENTPROMOTIONINFO);
            });
            vbInScrollCProm.getChildren().add(l);
        }
        Button add = new Button("Bring Friend");
        vbInScrollCProm.getChildren().add(add);
    }


}

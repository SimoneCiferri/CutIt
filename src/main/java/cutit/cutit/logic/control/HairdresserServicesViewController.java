package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class HairdresserServicesViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScrollHS;

    public boolean initialize() throws IOException {
        vbInScrollHS.setSpacing(15);
        showHairServ();
        return true;
    }

    private void showHairServ() {
        Button add = new Button("Add Service");
        add.setOnMouseClicked((MouseEvent) -> {
            goAddService();
        });
        vbInScrollHS.getChildren().add(add);
        for(Integer i=0; i<4; i++) {
            Label l = new Label("Service"+i.toString());
            l.setPrefSize(895, 130);
            l.setMinSize(895, 130);
            l.setMaxSize(895, 130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0, 0, 10, 20));
            l.setOnMouseClicked((MouseEvent) -> {
                Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERSERVICEINFO);
            });
            vbInScrollHS.getChildren().add(l);
        }
    }

    private void goAddService() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERADDSERVICE);
    }

}

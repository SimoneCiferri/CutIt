package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.PortUnreachableException;

public class HairdresserAppointmentsViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScrollHApp;

    public boolean initialize() throws IOException {
        vbInScrollHApp.setSpacing(15);
        showAppointments();
        return true;
    }

    private void showAppointments() {
        for(Integer i=0; i<6; i++){
            Label l = new Label("Appointment"+i.toString());
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> {
                Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERAPPINFO);
            });
            vbInScrollHApp.getChildren().add(l);
        }
    }

}

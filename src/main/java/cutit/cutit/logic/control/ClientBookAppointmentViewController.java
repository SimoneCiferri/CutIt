package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.time.LocalDate;

public class ClientBookAppointmentViewController {

    static String startAppointment = "";

    @FXML
    private BorderPane bpInBookApp;

    @FXML
    private Label labelDate, label830;

    @FXML
    private DatePicker dtPicker;

    @FXML
    public boolean bookAppNext() {
        Facade.getInstance().decorateView(ViewLayout.CLIENTBOOKAPPFORM);
        return true;
    }

    @FXML
    public boolean backToShopInfo() {
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        return true;
    }

    @FXML
    private void set830(){
        labelDate.setText(dtPicker.getValue().toString() + "T" + "08:30:00");
        startAppointment = dtPicker.getValue().toString() + "T" + "08:30:00";
        label830.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
    }

    public boolean initialize(){
        System.out.println("Book App page");
        dtPicker.setValue(LocalDate.now());
        return true;
    }

    public static String getAppDate(){
        return startAppointment;
    }

}

package cutit.controller.bookappointment;

import cutit.decorator.ViewLayout1;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;

public class CustomerAppointmentInfoViewController {

    @FXML
    private Label lDate, lAppTimeSlot, lShopName, lService;

    @FXML
    public boolean goBackToAppList(){
        Facade.getInstance().decorateView(ViewLayout1.CUSTOMERAPPOINTMENTS);
        return true;
    }

    public void fillView(LocalDateTime appStartTime, LocalDateTime appEndTime, String appService, String shopName){
        lDate.setText(appStartTime.toLocalDate().toString());
        lAppTimeSlot.setText(appStartTime.toLocalTime() + " - " + appEndTime.toLocalTime());
        lShopName.setText(shopName);
        lService.setText(appService);
    }

}

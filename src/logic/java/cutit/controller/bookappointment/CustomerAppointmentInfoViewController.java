package cutit.controller.bookappointment;

import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;

public class CustomerAppointmentInfoViewController {

    private String shopName;

    @FXML
    private Label lDate, lShopName, lService, lPromotion;

    public boolean initialize() {
        System.out.println("CONTROLLER GRAFICO CUSTOMERAPPOINTMENTINFOVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goBackToAppList(){
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERAPPOINTMENTS);
        return true;
    }

    public void fillView(LocalDateTime appStartTime, LocalDateTime appEndTime, String appService, String shopName){
        this.shopName = shopName;
        lDate.setText(appStartTime.toLocalDate() + " at " + appStartTime.toLocalTime());
        lShopName.setText(shopName);
        lService.setText(appService);
    }

}

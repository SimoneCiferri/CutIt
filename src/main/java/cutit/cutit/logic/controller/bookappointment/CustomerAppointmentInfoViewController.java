package cutit.cutit.logic.controller.bookappointment;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CustomerAppointmentInfoViewController {

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

    public void fillView(String appStartTime, String appEndTime, String appService, String appShop){
        lDate.setText(appStartTime);
        lShopName.setText(appShop);
        lService.setText(appService);
    }

}

package cutit.controller.bookappointment.secondui;

import cutit.controller.getlocationdirections.GetLocationDirectionsGoogleMapsViewController1;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.GetLocationDirectionsGoogleMapsView1;
import cutit.facade.Facade;
import cutit.facade.Facade2;
import javafx.fxml.FXML;

public class CustomerBookAppointmentViewController2 {


    @FXML
    public void initialize(){
        //Da fare
    }

    @FXML
    private void getDirections(){
        Facade2.getInstance().decorateView2(ViewLayout2.);
        GetLocationDirectionsGoogleMapsView1 view = (GetLocationDirectionsGoogleMapsView1) Facade.getInstance().getViewMap().get(ViewLayout1.GMAPS);
        GetLocationDirectionsGoogleMapsViewController1 viewController = (GetLocationDirectionsGoogleMapsViewController1) view.getLoadedViewController1(ViewLayout1.GMAPS);
        bookAppointmentController.getShopDirections(viewController, shopBeanUQ);
    }
}

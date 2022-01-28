package cutit.controller.bookappointment.secondui;

import cutit.bean.CustomerBean;
import cutit.bean.ShopBeanInterface;
import cutit.controller.bookappointment.BookAppointmentController;
import cutit.controller.getlocationdirections.GetLocationDirectionsGoogleMapsViewController2;
import cutit.decorator.ViewLayout1;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.GetLocationDirectionsGoogleMapsView2;
import cutit.facade.Facade2;
import javafx.fxml.FXML;

public class CustomerBookAppointmentViewController2 {

    private CustomerBean customerBeanSecondUI;
    private ShopBeanInterface shopBean;
    private BookAppointmentController bookAppointmentController;

    @FXML
    public void initialize(){
        //Da fare
        bookAppointmentController = new BookAppointmentController();
    }

    public void fillView(CustomerBean customerBeanFirstUI, ShopBeanInterface shopBean){
        this.customerBeanSecondUI = customerBeanFirstUI;
        this.shopBean = shopBean;
    }
}

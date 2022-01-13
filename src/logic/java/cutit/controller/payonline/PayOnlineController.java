package cutit.controller.payonline;

import cutit.bean.PayOnlineBean;
import cutit.bean.firstui.AppointmentBeanFirstUI;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;

public class PayOnlineController {

    public Boolean payAppointment(AppointmentBeanFirstUI appBean){
        System.out.println("CONTROLLER APPLICATIVO -> paying Appointment (data from AppointmentBean passed by BookAppointmentController)");
        return true;
    }

    public void getData(PayOnlineBean payOnlineBean){
        String shopName = payOnlineBean.getPaymentShopName();
        String servicePrice = payOnlineBean.getServicePrice();
        //paga!
    }


}

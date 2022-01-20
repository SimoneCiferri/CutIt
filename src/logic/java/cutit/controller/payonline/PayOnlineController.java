package cutit.controller.payonline;

import cutit.bean.AppointmentBean;
import cutit.bean.PayOnlineBean;

public class PayOnlineController {

    public Boolean payAppointment(AppointmentBean appBean){
        System.out.println("CONTROLLER APPLICATIVO -> paying Appointment (data from AppointmentBean passed by BookAppointmentController)");
        return true;
    }

    public void getData(PayOnlineBean payOnlineBean){
        String shopName = payOnlineBean.getPaymentShopName();
        String servicePrice = payOnlineBean.getServicePrice();
        //paga!
    }


}

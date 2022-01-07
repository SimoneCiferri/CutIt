package cutit.controller.payonline;

import cutit.bean.AppointmentBean;

public class PayOnlineController {

    public Boolean payAppointment(AppointmentBean appBean){
        //paga
        System.out.println("CONTROLLER APPLICATIVO -> paying Appointment (data from AppointmentBean passed by BookAppointmentController)");
        return true;
    }
}

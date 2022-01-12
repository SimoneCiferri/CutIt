package cutit.controller.payonline;

import cutit.bean.firstui.AppointmentBeanFirstUI;

public class PayOnlineController {

    public Boolean payAppointment(AppointmentBeanFirstUI appBean){
        System.out.println("CONTROLLER APPLICATIVO -> paying Appointment (data from AppointmentBean passed by BookAppointmentController)");
        return true;
    }


}

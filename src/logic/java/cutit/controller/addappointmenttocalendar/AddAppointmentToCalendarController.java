package cutit.controller.addappointmenttocalendar;

import cutit.bean.AppointmentBean;
import cutit.bean.firstui.AppointmentBeanFirstUI;

public class AddAppointmentToCalendarController {

    public Boolean addToCalendar(AppointmentBean appBean){
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Adding Appointment to Calendar (data from AppointmentBean passed by BookAppointmentController)");
        return true;
    }

}

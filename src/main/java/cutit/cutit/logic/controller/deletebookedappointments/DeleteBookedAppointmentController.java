package cutit.cutit.logic.controller.deletebookedappointments;

import cutit.cutit.logic.bean.DeleteAppointmentBean;

public class DeleteBookedAppointmentController {

    public Boolean deleteAppointment(DeleteAppointmentBean deleteAppointmentBean){
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Deleting Appointment (data from DeleteAppointmentBean passed by my viewController)");
        return true;
    }

}
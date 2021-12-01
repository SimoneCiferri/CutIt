package cutit.cutit.logic.controller.applController;

import cutit.cutit.logic.bean.*;

public class BookAppointmentController {

    public Boolean compileAppointment(AppointmentBean appointmentBean, CustomerBean customerBean, PromotionBookAppBean promotionBookAppBean, ServiceBookAppBean serviceBookAppBean, ShopBookAppBean shopBookAppBean){
        //dovrò creare un Model (?) e passare le beans, in modo che queste si possano registrare come osservatori del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("Compiling Appointment (data from AppointmentBean, CustomerBean, PromotionBookAppBean, ServiceBookAppBean, ShopBookAppBean passed by my viewController)");
        return true;
    }

}

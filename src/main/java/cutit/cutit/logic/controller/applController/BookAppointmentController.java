package cutit.cutit.logic.controller.applController;

import cutit.cutit.logic.bean.*;

public class BookAppointmentController {

    private PayOnlineController payOnlineController;
    private RateShopController rateShopController;
    private AddShopToFavouritesController addShopToFavouritesController;
    private AddAppointmentToCalendarController addAppointmentToCalendarController;

    public Boolean compileAppointment(AppointmentBean appointmentBean){
        //dovrò passare le beans, in modo che queste si possano registrare come osservatori del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Compiling Appointment (data from AppointmentBean passed by my viewController)");
        if(payAppointment(appointmentBean)){
            return true;
        }
        return false;
    }

    private Boolean payAppointment(AppointmentBean appBean){
        payOnlineController = new PayOnlineController();
        payOnlineController.payAppointment(appBean);
        return true;
    }

    public Boolean rateShop(RateShopBean rateShopBean){
        rateShopController = new RateShopController();
        rateShopController.rateShop(rateShopBean);
        return true;
    }

    public Boolean addShopToFavourites(RateShopBean shopBean){
        addShopToFavouritesController = new AddShopToFavouritesController();
        addShopToFavouritesController.addToFavourites(shopBean);
        return true;
    }

    public Boolean addToCalendar(AppointmentBean appBean){
        addAppointmentToCalendarController = new AddAppointmentToCalendarController();
        addAppointmentToCalendarController.addToCalendar(appBean);
        return true;
    }
}

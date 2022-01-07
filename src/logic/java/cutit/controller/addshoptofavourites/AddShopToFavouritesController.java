package cutit.controller.addshoptofavourites;

import cutit.bean.RateShopBean;

public class AddShopToFavouritesController {

    public Boolean addToFavourites(RateShopBean shopBean){
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Adding to favourites Shop (data from RateShopBean passed by BookAppointmentController)");
        return true;
    }
}

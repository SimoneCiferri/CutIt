package cutit.controller.rateshop;

import cutit.bean.RateShopBean;
import cutit.bean.firstui.RateShopBeanUQ;


public class RateShopController {

    public Boolean rateShop(RateShopBean shopBean){
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Rating Shop (data from RateShopBean passed by BookAppointmentController)");
        return true;
    }

}

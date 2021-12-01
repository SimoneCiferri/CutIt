package cutit.cutit.logic.controller.applController;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.ShopBean;

public class RateShopController {

    public Boolean rateShop(CustomerBean customerBean, ShopBean shopBean){
        //dovrò creare un Model (?) e passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("Rating Shop (data from CustomerBean and ShopBean passed by my viewController)");
        return true;
    }

}

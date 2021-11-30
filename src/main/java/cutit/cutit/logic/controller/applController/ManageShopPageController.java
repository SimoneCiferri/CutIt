package cutit.cutit.logic.controller.applController;

import cutit.cutit.logic.bean.ShopBean;

public class ManageShopPageController {

    public boolean updateData(ShopBean bean){
        //dovrò creare un Model (?) e passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("Updating data (data from shop bean passed by my viewController)");
        return true;
    }
}

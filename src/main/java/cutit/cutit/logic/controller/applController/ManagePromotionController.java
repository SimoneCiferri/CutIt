package cutit.cutit.logic.controller.applController;

import cutit.cutit.logic.bean.ManagePromotionBean;

public class ManagePromotionController {

    public Boolean removePromotion(ManagePromotionBean managePromotionBean){
        //dovrò creare un Model (?) e passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("Deleting Promotion (data from ManagePromotionBean passed by my viewController)");
        return true;
    }

    public Boolean addPromotion(ManagePromotionBean managePromotionBean){
        //dovrò creare un Model (?) e passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("Adding Promotion (data from ManagePromotionBean passed by my viewController)");
        return true;
    }

}

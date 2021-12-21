package cutit.cutit.logic.controller.manageshoppage;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.database.dao.ShopDAO;
import cutit.cutit.logic.model.Shop;

public class ManageShopPageController {

    //riempito metodo updateData, non riesco a risolvere il problema
    public ShopBean updateData(ShopBean shopBean) throws Exception {
        //commentato il codice che mi da errore
        //Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopAddress(), shopBean.getPhoneNumber(), shopBean.getShopDescription());
        //ShopDAO.getInstance().insertShop(shop);
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Updating data (data from ShopBean passed by my viewController)");
        return shopBean;
    }
}

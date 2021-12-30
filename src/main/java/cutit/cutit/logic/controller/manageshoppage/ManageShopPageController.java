package cutit.cutit.logic.controller.manageshoppage;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.database.dao.ShopDAO;
import cutit.cutit.logic.model.Shop;

public class ManageShopPageController {

    public ShopBean updateShop(ShopBean shopBean) throws Exception {
        Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA(), shopBean.getAddress(), shopBean.getPhoneNumber(), shopBean.getEmployee(), shopBean.getShopDescription(), shopBean.getOpenTime(), shopBean.getCloseTime());
        shop.setOpenDays(shopBean.getOpenDays());
        ShopDAO.updateShop(shop);
        System.out.println("CONTROLLER APPLICATIVO -> Updating data (data from ShopBean passed by my viewController)");
        return shopBean;
    }
}

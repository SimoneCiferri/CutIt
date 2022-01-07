package cutit.controller.manageshoppage;

import cutit.bean.ShopBean;
import cutit.database.dao.ShopDAO;
import cutit.model.Shop;

public class ManageShopPageController {

    public ShopBean updateShop(ShopBean shopBean) throws Exception {
        Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA(), shopBean.getAddress(), shopBean.getPhoneNumber(), shopBean.getEmployee(), shopBean.getShopDescription(), shopBean.getOpenTime(), shopBean.getCloseTime());
        shop.setOpenDays(shopBean.getOpenDays());
        shop.setImages(shopBean.getImages());
        ShopDAO.updateShop(shop);
        System.out.println("CONTROLLER APPLICATIVO -> Updating data (data from ShopBean passed by my viewController)");
        return shopBean;
    }
}

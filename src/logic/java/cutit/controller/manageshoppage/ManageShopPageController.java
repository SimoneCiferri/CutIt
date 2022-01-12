package cutit.controller.manageshoppage;

import cutit.bean.ShopBean;
import cutit.database.dao.ShopDAO;
import cutit.log.LogWriter;
import cutit.model.Shop;

public class ManageShopPageController {

    public void updateShop(ShopBean shopBean) throws Exception {
        try{
            Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA(), shopBean.getAddress(), shopBean.getPhoneNumber(), shopBean.getEmployee(), shopBean.getShopDescription(), shopBean.getOpenTime(), shopBean.getCloseTime());
            shop.setOpenDays(shopBean.getOpenDays());
            shop.setImages(shopBean.getImages());
            ShopDAO.updateShop(shop);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }
}

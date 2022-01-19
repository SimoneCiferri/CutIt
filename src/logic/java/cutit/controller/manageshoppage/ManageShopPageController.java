package cutit.controller.manageshoppage;

import cutit.bean.ShopBean;
import cutit.database.dao.ShopDAO;
import cutit.exception.WrongInputDataException;
import cutit.log.LogWriter;
import cutit.model.Shop;

public class ManageShopPageController {

    public void updateShop(ShopBean shopBean) throws Exception {
        try{
            if(semanticCheck(shopBean)){
                Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA(), shopBean.getShopAddress(), shopBean.getShopPhoneNumber(), shopBean.getShopEmployee(), shopBean.getShopDescription(), shopBean.getShopOpenTime(), shopBean.getShopCloseTime());
                shop.setOpenDays(shopBean.getShopOpenDays());
                shop.setImages(shopBean.getImages());
                ShopDAO.updateShop(shop);
            } else {
                throw new WrongInputDataException("Open Time is before Close Time. Please check your data.");
            }
        } catch (WrongInputDataException wde){
            throw wde;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private boolean semanticCheck(ShopBean shopBean) {
        return shopBean.getShopOpenTime().isBefore(shopBean.getShopCloseTime());
    }
}

package cutit.controller.manageshoppage;

import cutit.bean.ShopBeanInterface;
import cutit.database.dao.ShopDAO;
import cutit.exception.WrongInputDataException;
import cutit.log.LogWriter;
import cutit.model.Shop;

import java.time.LocalTime;

public class ManageShopPageController {

    public void updateShop(ShopBeanInterface shopBean) throws Exception {
        try{
            if(semanticCheck(shopBean.getShopOpenTime(), shopBean.getShopCloseTime())){
                Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA());
                shop.setAddress(shopBean.getShopAddress());
                shop.setPhoneNumber(shopBean.getShopPhoneNumber());
                shop.setEmployee(shopBean.getShopEmployee());
                shop.setDescription(shopBean.getShopDescription());
                shop.setOpenTime(shopBean.getShopOpenTime());
                shop.setCloseTime(shopBean.getShopCloseTime());
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

    private boolean semanticCheck(LocalTime openTime, LocalTime closeTime) {
        return openTime.isBefore(closeTime);
    }
}

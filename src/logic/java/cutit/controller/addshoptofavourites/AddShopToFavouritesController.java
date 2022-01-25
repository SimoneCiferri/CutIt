package cutit.controller.addshoptofavourites;

import cutit.database.dao.ShopDAO;
import cutit.exception.DuplicatedRecordException;
import cutit.log.LogWriter;

public class AddShopToFavouritesController {

    public Boolean addToFavourites(String customerEmail, String shopName) throws Exception{
        try {
            ShopDAO.insertFavoriteShop(customerEmail, shopName);
            return true;
        } catch (DuplicatedRecordException de){
            throw de;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }
}

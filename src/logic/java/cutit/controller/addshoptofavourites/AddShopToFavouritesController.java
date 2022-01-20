package cutit.controller.addshoptofavourites;

import cutit.database.dao.FavoriteShopsDAO;
import cutit.exception.DuplicatedRecordException;
import cutit.log.LogWriter;

public class AddShopToFavouritesController {

    public Boolean addToFavourites(String customerEmail, String shopName) throws Exception{
        try {
            FavoriteShopsDAO.insertFavoriteShop(customerEmail, shopName);
            return true;
        } catch (DuplicatedRecordException de){
            throw de;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }
}

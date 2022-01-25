package cutit.controller.addshoptofavourites;

import cutit.database.dao.ShopDAO;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.log.LogWriter;

import java.sql.SQLException;

public class AddShopToFavouritesController {

    public Boolean addToFavourites(String customerEmail, String shopName) throws DBConnectionException, SQLException, DuplicatedRecordException{
        try {
            ShopDAO.insertFavoriteShop(customerEmail, shopName);
            return true;
        } catch (DBConnectionException | SQLException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }
}

package cutit.controller.addshoptofavourites;

import cutit.bean.firstui.RateShopBeanUQ;
import cutit.database.dao.FavoriteShopsDAO;
import cutit.exception.DuplicatedRecordException;
import cutit.log.LogWriter;
import cutit.model.Customer;

public class AddShopToFavouritesController {

    public Boolean addToFavourites(String customerEmail, String shopName) throws Exception{
        try {
            System.out.println("CONTROLLER APPLICATIVO -> Adding to favourites Shop (data from RateShopBean passed by BookAppointmentController)");
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

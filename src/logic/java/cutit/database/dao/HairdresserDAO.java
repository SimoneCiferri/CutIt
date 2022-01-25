package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.HairdresserQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.RecordNotFoundException;
import cutit.model.Hairdresser;
import cutit.model.Shop;
import cutit.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HairdresserDAO {

    public static Boolean checkPIVA(String piva) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = HairdresserQueries.checkIfPIVAExists(stm, piva);
        rs.first();
        int exists = rs.getInt(1);
        rs.close();
        stm.close();
        if(exists == 0){
            return false;
        }else{
            throw new DuplicatedRecordException("User with selected PIVA already exists");
        }
    }

    public static void insertNewHairdresser(Hairdresser hairdresser, String shopName) throws Exception {
        UserDAO.insertNewUser(hairdresser);
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        HairdresserQueries.insertHairdresser(stm, hairdresser.getpIVA(), hairdresser.getUserID(), hairdresser.getName(), hairdresser.getSurname());
        stm.close();
        Shop shop = new Shop(shopName, hairdresser.getpIVA());
        ShopDAO.insertShop(shop);
    }

    public static Hairdresser getHairdresser(User user) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = HairdresserQueries.getHairdresser(stm, user.getUserID());
        if(!rs.first()){
            String message = "Hairdresser not found";
            throw new RecordNotFoundException(message);
        }else{
            rs.first();
            String piva = rs.getString(1);
            String hEmail = rs.getString(2);
            String name = rs.getString(3);
            String surname = rs.getString(4);
            Hairdresser hairdresser = new Hairdresser(hEmail, user.getPwd(), user.getRole(), name, surname, piva);
            Shop shop = ShopDAO.getShopFromHairdresser(hairdresser);
            hairdresser.setShop(shop);
            rs.close();
            stm.close();
            return hairdresser;
        }
    }

    public static void logout() throws DBConnectionException {
        DBConnection.getInstance().closeConnection();
    }

}

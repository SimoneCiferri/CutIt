package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.HairdresserQueries;
import cutit.database.query.ShopQueries;
import cutit.database.query.UserQueries;
import cutit.factory.AlertFactory;
import cutit.model.Hairdresser;
import cutit.model.Shop;
import cutit.model.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HairdresserDAO {

    public static Boolean checkIfPIVAExists(String piva) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = HairdresserQueries.checkIfPIVAExists(stm, piva);
        rs.first();
        int exists = rs.getInt(1);
        rs.close();
        if (stm != null) {
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
        return exists != 0;
    }

    public static void insertNewHairdresser(Hairdresser hairdresser, String shopName) throws Exception {
        UserDAO.insertNewUser(hairdresser);
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        HairdresserQueries.insertHairdresser(stm, hairdresser.getpIVA(), hairdresser.getUserID(), hairdresser.getName(), hairdresser.getSurname());
        if (stm != null) {
            stm.close();
        }
        Shop shop = new Shop(shopName, hairdresser.getpIVA());
        ShopDAO.insertShop(shop);
        //DBConnection.getInstance().closeConnection();
    }

    public static Hairdresser getHairdresser(User user) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = HairdresserQueries.getHairdresser(stm, user.getUserID());
        if(!rs.first()){
            Exception e = new Exception("No user Found matching with name: "+ user.getUserID());
            throw e;
        }else{
            String piva = rs.getString(1);
            String hEmail = rs.getString(2);
            String name = rs.getString(3);
            String surname = rs.getString(4);
            Hairdresser hairdresser = new Hairdresser(hEmail, user.getPwd(), user.getRole(), name, surname, piva);
            Shop shop = ShopDAO.getShop(hairdresser);
            hairdresser.setShop(shop);
            rs.close();
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return hairdresser;
        }
    }

}

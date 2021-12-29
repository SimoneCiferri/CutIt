package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.HairdresserQueries;
import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.Shop;
import cutit.cutit.logic.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HairdresserDAO {


    public static void insertNewHairdresser(Hairdresser hairdresser, String shopName) throws Exception {
        UserDAO.insertNewUser(hairdresser);

        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        HairdresserQueries.insertHairdresser(stm, hairdresser.getpIVA(), hairdresser.getUserID(), hairdresser.getName(), hairdresser.getSurname());
        if(stm != null){
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
            //retrieve dello shop!
            rs.close();
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return hairdresser;
        }
    }

}

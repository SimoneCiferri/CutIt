package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.ShopQueries;
import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.Shop;
import cutit.cutit.logic.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShopDAO {

    private static ShopDAO instance = null;

    private ShopDAO(){}

    public static synchronized ShopDAO getInstance(){
        if(instance == null){
            instance = new ShopDAO();
        }
        return instance;
    }

    public static void insertShop(Shop shop) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ShopQueries.insertShop(stm, shop.getPIVA(), shop.getShopName(), " ", " ");
        if(stm != null){
            stm.close();
        }
    }

    public static Shop getShopFromUser(User user) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getShopFromUser(stm, user.getUserID());
        if(!rs.first()){
            Exception e = new Exception("No user Found matching with name: "+ user.getUserID());
            throw e;
        }else{
            String hPIVA = rs.getString("Hairdresser_PIVA");
            String shopName = rs.getString("ShopName");
            System.out.println(shopName);
            Shop shop = new Shop(shopName, hPIVA);
            rs.close();
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return shop;
        }
    }

    public static Shop getShop(Hairdresser hairdresser) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getShop(stm, hairdresser.getpIVA());
        if(!rs.first()){
            Exception e = new Exception("No user Found matching with name: "+ hairdresser.getUserID());
            throw e;
        }else{
            String hPiva = rs.getString("Hairdresser_PIVA");
            String shopName = rs.getString("ShopName");
            System.out.println(shopName);
            Shop shop = new Shop(shopName, hPiva);
            rs.close();
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return shop;
        }
    }



}

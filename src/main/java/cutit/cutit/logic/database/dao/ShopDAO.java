package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.ShopQueries;
import cutit.cutit.logic.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShopDAO {

    public static void insertShop(Shop shop) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ShopQueries.insertShop(stm, shop.getShopName(), shop.getpIVA());
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
            String shopName = rs.getString(1);
            String employee = rs.getString(2);
            String address = rs.getString(3);
            String hPiva = rs.getString(4);
            String phoneNumber = rs.getString(5);
            String description = rs.getString(6);
            String openTime = rs.getString(7);
            String closeTime = rs.getString(8);
            Shop shop = new Shop(shopName, hPiva, address, phoneNumber, employee, description, dateFromString(openTime), dateFromString(closeTime));
            List<Integer> openDays = getOpenDays(shop.getpIVA());
            shop.setOpenDays(openDays);
            //manca il retrieve delle immagini
            List<Promotion> allPromotions = PromotionDAO.getAllPromotion(shop);
            shop.setPromotions(allPromotions);
            List<Service> services = ServiceDAO.getALlServices(shop);
            shop.setServices(services);
            //ho messo una lista di appuntamenti vuota senno dava NPE
            List<Appointment> app = new ArrayList<>();
            shop.setAllAppointments(app);
            rs.close();
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return shop;
        }
    }



    private static List<Integer> getOpenDays(String shopPiva) throws Exception {
        List<Integer> openDays = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getOpenDays(stm, shopPiva);
        if (rs.first()) {
            rs.first();
            do {
                Integer day = rs.getInt(2);
                openDays.add(day);
            } while (rs.next());
            rs.close();
            if (stm != null) {
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
        }
        return openDays;
    }


    private static LocalTime dateFromString(String openTime) {
        return LocalTime.parse(openTime);
    }
    private static String stringFromTime(LocalTime data){
        return data.toString();
    }


}

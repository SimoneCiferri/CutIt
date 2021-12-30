package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.ShopQueries;
import cutit.cutit.logic.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ShopDAO {

    public static void insertShop(Shop shop) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ShopQueries.insertShop(stm, shop.getShopName(), shop.getpIVA());
        for(int i =1; i<8; i++){
            ShopQueries.insertOpenDay(stm, shop.getShopName(), i, 0);
        }
        if(stm != null){
            stm.close();
        }
    }

    public static Shop getShopFromName(String shopName) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getShopFromName(stm, shopName);
        if(!rs.first()){
            Exception e = new Exception("No Shop matching with name: "+ shopName);
            throw e;
        }else{
            String hPIVA = rs.getString("Hairdresser_PIVA");
            String shopNAme = rs.getString("ShopName");
            Shop shop = new Shop(shopNAme, hPIVA);
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
            Map<Integer, Boolean> openDays = getOpenDays(shop.getShopName());
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

    private static Map<Integer, Boolean> getOpenDays(String shopName) throws Exception {
        Map<Integer, Boolean> openDays = new HashMap<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getOpenDays(stm, shopName);
        if (rs.first()) {
            rs.first();
            do {
                Integer day = rs.getInt(2);
                Boolean open = rs.getBoolean(3);
                openDays.put(day, open);
            } while (rs.next());
            rs.close();
            if (stm != null) {
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
        }
        return openDays;
    }

    public static void updateShop(Shop shop) throws Exception{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ShopQueries.updateShop(stm, shop.getShopName(), shop.getAddress(), shop.getPhoneNumber(), shop.getEmployee(), shop.getDescription(), shop.getOpenTime().toString(), shop.getCloseTime().toString());
        for(int i = 0; i<shop.getOpenDays().size();i++){
            if(shop.getOpenDays().get(i+1)){
                ShopQueries.updateOpenDay(stm, shop.getShopName(), i+1, 1);
            }else{
                ShopQueries.updateOpenDay(stm, shop.getShopName(), i+1, 0);
            }
        }
        //ShopQueries.insertImage(stm, shop.getShopName(), 1, shop.getImages().get(0));
        if(stm != null){
            stm.close();
        }
    }


    private static LocalTime dateFromString(String openTime) {
        return LocalTime.parse(openTime);
    }
    private static String stringFromTime(LocalTime data){
        return data.toString();
    }




}

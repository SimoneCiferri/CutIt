package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.ShopQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.RecordNotFoundException;
import cutit.model.*;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.*;

public class ShopDAO {

    public static Boolean checkIfShopExists(String shopName) throws Exception {
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ShopQueries.checkIfShopExists(stm, shopName);
            int exists = rs.getInt(1);
            rs.close();
            stm.close();
            //DBConnection.getInstance().closeConnection();
            return exists != 0;
        } catch (SQLException se){
            throw new DBConnectionException(se.getMessage(), se.getCause());
        }

    }

    public static void insertShop(Shop shop) throws Exception {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ShopQueries.insertShop(stm, shop.getShopName(), shop.getpIVA());
            for (int i = 1; i < 8; i++) {
                ShopQueries.insertOpenDay(stm, shop.getShopName(), i, 0);
            }
            stm.close();
        } catch (SQLException se) {
            throw new DBConnectionException(se.getMessage(), se.getCause());
        }
    }

    public static Shop getShopFromName(String shopName) throws Exception {
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ShopQueries.getShopFromName(stm, shopName);
            if(!rs.first()){
                Throwable cause = new Throwable("Shop not found matching with name " + shopName);
                throw new RecordNotFoundException(cause);
            }else{
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

                List<File> images = getImages(shop);
                shop.setImages(images);

                List<Promotion> allPromotions = PromotionDAO.getAllPromotion(shop);
                shop.setPromotions(allPromotions);
                List<Service> services = ServiceDAO.getALlServices(shop);
                shop.setServices(services);
                rs.close();
                stm.close();
                //DBConnection.getInstance().closeConnection();
                return shop;
            }
        } catch (SQLException se) {
            throw new DBConnectionException(se.getMessage(), se.getCause());
        }

    }

    public static Shop getShop(Hairdresser hairdresser) throws Exception {
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ShopQueries.getShop(stm, hairdresser.getpIVA());
            if(!rs.first()){
                Throwable cause = new Throwable("Shop not found matching with piva " + hairdresser.getpIVA());
                throw new RecordNotFoundException(cause);
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

                List<File> images = getImages(shop);
                shop.setImages(images);

                List<Promotion> allPromotions = PromotionDAO.getAllPromotion(shop);
                shop.setPromotions(allPromotions);
                List<Service> services = ServiceDAO.getALlServices(shop);
                shop.setServices(services);
                //ho messo una lista di appuntamenti vuota senno dava NPE
                List<Appointment> app = new ArrayList<>();
                shop.setAllAppointments(app);
                rs.close();
                stm.close();
                //DBConnection.getInstance().closeConnection();
                return shop;
            }
        } catch (SQLException se) {
            throw new DBConnectionException(se.getMessage(), se.getCause());
        }

    }

    private static Map<Integer, Boolean> getOpenDays(String shopName) throws Exception {
        try{
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
                stm.close();
                //DBConnection.getInstance().closeConnection();
            }
            return openDays;
        } catch (SQLException se) {
            throw new DBConnectionException(se.getMessage(), se.getCause());
        }
    }


    private static List<File> getImages(Shop shop) throws Exception{
        try{
            List<File> images = new ArrayList<File>();
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ShopQueries.getImages(stm, shop.getShopName());
            if (rs.first()) {
                rs.first();
                int i =0;
                do {
                    byte[] image = rs.getBytes(3);
                    File file = new File("src/logic/resources/cutit/cutit/tmpfiles/image" + i + ".tmp");
                    OutputStream out = new FileOutputStream(file);
                    out.write(image);
                    images.add(file);
                    out.close();
                    i++;
                } while (rs.next());
                rs.close();
                stm.close();
                //DBConnection.getInstance().closeConnection();
            }
            return images;
        } catch (SQLException se) {
            throw new DBConnectionException(se.getMessage(), se.getCause());
        }
    }

    public static void updateShop(Shop shop) throws Exception{
        try{
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
            if(!shop.getImages().isEmpty()){
                ShopQueries.deleteAllImages(stm, shop.getShopName());
                for(int j = 0;j<shop.getImages().size();j++){
                    ShopQueries.insertImage(stm, String.valueOf(j), shop.getImages().get(j), shop.getShopName());
                }
            }
            stm.close();
        } catch (SQLException se) {
            throw new DBConnectionException(se.getMessage(), se.getCause());
        }

    }

    public static List<Shop> getShops() throws Exception {
        try{
            List<Shop> shopList = new ArrayList<>();
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ShopQueries.getShops(stm);
            if (rs.first()) {
                rs.first();
                do {
                    String shopNAme = rs.getString(1);
                    String shopAddress = rs.getString(3);
                    String hPIVA = rs.getString(4);
                    Shop shop = new Shop(shopNAme, hPIVA);
                    shop.setAddress(shopAddress);
                    shopList.add(shop);
                } while (rs.next());
                rs.close();
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return shopList;
        } catch (SQLException se) {
            throw new DBConnectionException(se.getMessage(), se.getCause());
        }
    }


    private static LocalTime dateFromString(String openTime) {
        return LocalTime.parse(openTime);
    }

    private static String stringFromTime(LocalTime data){
        return data.toString();
    }




}

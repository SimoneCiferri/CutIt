package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.ShopQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
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

    private static final String SERVICE_NOT_FOUND = "Service not found";

    private ShopDAO(){}

    public static Boolean checkShop(String shopName) throws DBConnectionException, SQLException, DuplicatedRecordException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.checkIfShopExists(stm, shopName);
        rs.first();
        int exists = rs.getInt(1);
        rs.close();
        stm.close();
        if(exists == 0){
            return false;
        }else{
            throw new DuplicatedRecordException("Shop with selected name already exists");
        }
    }

    public static void insertShop(Shop shop) throws DBConnectionException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ShopQueries.insertShop(stm, shop.getShopName(), shop.getpIVA());
        for (int i = 1; i < 8; i++) {
            ShopQueries.insertOpenDay(stm, shop.getShopName(), i, 0);
        }
        stm.close();
    }

    public static Shop getShopFromName(String shopName) throws DBConnectionException, SQLException, RecordNotFoundException, IOException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getShopFromName(stm, shopName);
        if (!rs.first()) {
            throw new RecordNotFoundException(SERVICE_NOT_FOUND);
        } else {
            rs.first();
            Shop shop = retrieveShopFromResultSet(rs, shopName);
            rs.close();
            stm.close();
            return shop;
        }
    }

    public static Shop getShopLite(String shopName) throws DBConnectionException, SQLException, RecordNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getShopFromName(stm, shopName);
        if (!rs.first()) {
            throw new RecordNotFoundException(SERVICE_NOT_FOUND);
        } else {
            rs.first();
            String address = rs.getString(3);
            String hPiva = rs.getString(4);
            String phoneNumber = rs.getString(5);
            Shop shop = new Shop(shopName, hPiva);
            shop.setAddress(address);
            shop.setPhoneNumber(phoneNumber);
            rs.close();
            stm.close();
            return shop;
        }
    }

    public static Shop getShopFromHairdresser(Hairdresser hairdresser) throws DBConnectionException, SQLException, RecordNotFoundException, IOException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getShop(stm, hairdresser.getpIVA());
        if (!rs.first()) {
            throw new RecordNotFoundException(SERVICE_NOT_FOUND);
        } else {
            rs.first();
            String shopName = rs.getString(1);
            Shop shop = retrieveShopFromResultSet(rs, shopName);
            rs.close();
            stm.close();
            return shop;
        }
    }

    private static Map<Integer, Boolean> getOpenDays(String shopName) throws DBConnectionException,SQLException {
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
        }
        return openDays;
    }


    private static List<File> getImages(String shopName) throws DBConnectionException, SQLException {
        List<File> images = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getImages(stm, shopName);
        if (rs.first()) {
            rs.first();
            int i = 0;
            do {
                byte[] image = rs.getBytes(3);
                File file = new File("src/logic/resources/cutit/cutit/tmpfiles/image" + i + shopName + ".tmp");
                try(OutputStream out = new FileOutputStream(file)) {
                    out.write(image);
                    images.add(file);
                    i++;
                } catch (IOException e){
                    i++;
                }
            } while (rs.next());
            rs.close();
            stm.close();
        }
        return images;
    }

    public static void updateShop(Shop shop) throws DBConnectionException, SQLException, FileNotFoundException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        List<String> openAndCloseTimeList = new ArrayList<>();
        openAndCloseTimeList.add(0, stringFromTime(shop.getOpenTime()));
        openAndCloseTimeList.add(1, stringFromTime(shop.getCloseTime()));
        ShopQueries.updateShop(stm, shop.getShopName(), shop.getAddress(), shop.getPhoneNumber(), shop.getEmployee(), shop.getDescription(), openAndCloseTimeList);
        for (int i = 0; i < shop.getOpenDays().size(); i++) {
            if (shop.getOpenDays().get(i + 1)) {
                ShopQueries.updateOpenDays(stm, shop.getShopName(), i + 1, 1);
            } else {
                ShopQueries.updateOpenDays(stm, shop.getShopName(), i + 1, 0);
            }
        }
        if (!shop.getImages().isEmpty()) {
            ShopQueries.deleteAllImages(stm, shop.getShopName());
            for (int j = 0; j < shop.getImages().size(); j++) {
                ShopQueries.insertImage(String.valueOf(j), shop.getImages().get(j), shop.getShopName());
            }
        }
        stm.close();
    }

    public static List<Shop> getDefaultShops() throws DBConnectionException, SQLException, IOException {
        List<Shop> shopList = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getShops(stm);
        if (rs.first()) {
            rs.first();
            do {
                String shopName = rs.getString(1);
                String shopAddress = rs.getString(3);
                String hPIVA = rs.getString(4);
                Shop shop = new Shop(shopName, hPIVA);
                shop.setAddress(shopAddress);
                List<File> images = getImages(shop.getShopName());
                shop.setImages(images);
                shopList.add(shop);
            } while (rs.next());
            rs.close();
            stm.close();
        }
        return shopList;
    }

    private static Shop retrieveShopFromResultSet(ResultSet rs, String shopName) throws SQLException, DBConnectionException, IOException, RecordNotFoundException{
        String employee = rs.getString(2);
        String address = rs.getString(3);
        String hPiva = rs.getString(4);
        String phoneNumber = rs.getString(5);
        String description = rs.getString(6);
        String openTime = rs.getString(7);
        String closeTime = rs.getString(8);
        Shop shop = new Shop(shopName, hPiva);
        shop.setAddress(address);
        shop.setPhoneNumber(phoneNumber);
        shop.setEmployee(employee);
        shop.setDescription(description);
        shop.setOpenTime(timeFromString(openTime));
        shop.setCloseTime(timeFromString(closeTime));
        Map<Integer, Boolean> openDays = getOpenDays(shop.getShopName());
        shop.setOpenDays(openDays);
        List<File> images = getImages(shop.getShopName());
        shop.setImages(images);
        List<Promotion> allPromotions = PromotionDAO.getAllPromotion(shop.getShopName());
        shop.setPromotions(allPromotions);
        List<Service> services = ServiceDAO.getAllServices(shop.getShopName());
        shop.setServices(services);
        List<Appointment> app = AppointmentDAO.getAllShopAppointments(shop);
        shop.setAllAppointments(app);
        return shop;
    }

    public static List<Shop> getFavouritesShops(String customerEmail) throws DBConnectionException, SQLException, RecordNotFoundException, IOException {
        List<Shop> favList = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getAllFavouritesShop(stm, customerEmail);
        if (rs.first()) {
            rs.first();
            do {
                String sName = rs.getString(2);
                Shop s = getShopFromName(sName);
                favList.add(s);
            } while (rs.next());
            rs.close();
            stm.close();
        }
        return favList;
    }

    public static void insertFavoriteShop(String customerEmail, String shopNameF) throws DBConnectionException, SQLException, DuplicatedRecordException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ShopQueries.getAllFavouritesShop(stm, customerEmail);
        while (rs.next()) {
            String shopN = rs.getString(2);
            if(Objects.equals(shopNameF, shopN)){
                throw new DuplicatedRecordException(shopNameF + " already added!");
            }
        }
        rs.close();
        stm.close();
        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ShopQueries.insertShopToFav(stm, customerEmail, shopNameF);
        stm.close();
    }

    private static LocalTime timeFromString(String openTime) {
        return LocalTime.parse(openTime);
    }

    private static String stringFromTime(LocalTime data){
        return data.toString();
    }




}

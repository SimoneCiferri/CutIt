package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.PromotionQueries;
import cutit.database.query.ServiceQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.RecordNotFoundException;
import cutit.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PromotionDAO {

    public static void insertPromotion(Promotion promotion, String serviceName, String shopName) throws Exception {
        Service service = ServiceDAO.getService(shopName, serviceName);
        promotion.setService(service);

        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getAllPromotion(stm, shopName);
        while (rs.next()) {
            String code = rs.getString(1);
            if (Objects.equals(promotion.getCode(), code)) {
                throw new DuplicatedRecordException(service.getServiceName() + " already exists!");
            }
        }
        rs.close();
        stm.close();
        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        PromotionQueries.insertPromotion(stm, promotion.getCode(), promotion.getOffValue(), stringFromData(promotion.getExpireDate()), promotion.getService().getServiceName(), promotion.getService().getShopName());
        stm.close();
    }

    public static List<Promotion> getAllPromotion(String shopName) throws Exception {
        List<Promotion> promotionsList = new ArrayList<Promotion>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getAllPromotion(stm, shopName);
        if (rs.first()) {
            rs.first();
            do {
                String promotionCode = rs.getString(1);
                Integer offValue = rs.getInt(2);
                String expireDate = rs.getString(3);
                String promService_Name = rs.getString(4);
                String promService_ShopName = rs.getString(5);
                Service service = ServiceDAO.getService(promService_ShopName, promService_Name);
                Promotion p = new Promotion(promotionCode, offValue, dataFromString(expireDate), service);
                if(!LocalDate.now().isAfter(p.getExpireDate())){
                    promotionsList.add(p);
                }
            } while (rs.next());
            rs.close();
            stm.close();
            //DBConnection.getInstance().closeConnection();
        }
        return promotionsList;
    }

    public static List<Promotion> getAllCustomerPromotion(String customerEmail) throws Exception {
        List<Promotion> promotionsList = new ArrayList<Promotion>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getAllCustomerPromotion(stm, customerEmail);
        if (rs.first()) {
            rs.first();
            do {
                String promotionCode = rs.getString("Code");
                Integer offValue = rs.getInt("Off");
                String expireDate = rs.getString("ExpireDate");
                String promService_Name = rs.getString("Service_Name");
                String promService_ShopName = rs.getString("Service_Shop_ShopName");
                Service service = ServiceDAO.getService(promService_ShopName, promService_Name);
                Promotion p = new Promotion(promotionCode, offValue, dataFromString(expireDate), service);
                if(!LocalDate.now().isAfter(p.getExpireDate())){
                    promotionsList.add(p);
                }
            } while (rs.next());
            rs.close();
            stm.close();
            //DBConnection.getInstance().closeConnection();
        }
        return promotionsList;
    }

    /*public static Promotion checkPersonalPromotion(Customer customer) throws Exception {
        List<Promotion> promotionsList = new ArrayList<Promotion>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getAllCustomerPromotion(stm, customer.getUserID());
        if (rs.first()) {
            rs.first();
            do {
                String promotionCode = rs.getString(1);
                Integer offValue = rs.getInt(2);
                String expireDate = rs.getString(3);
                String promService_Name = rs.getString(4);
                String promService_ShopName = rs.getString(5);
                Service service = ServiceDAO.getService(promService_ShopName, promService_Name);
                Promotion p = new Promotion(promotionCode, offValue, dataFromString(expireDate), service);
                promotionsList.add(p);
            } while (rs.next());
            rs.close();
            stm.close();
            //DBConnection.getInstance().closeConnection();
        }
        return promotionsList;
    }*/

    public static Promotion getPersonalPromotion(String customerEmail, String promCode) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getPersonalPromotion(stm, customerEmail, promCode);
        if (!rs.first()) {
            String message = "Promotion not available";
            throw new RecordNotFoundException(message);
        } else{
            Promotion prom = getPromotion(promCode);
            rs.close();
            stm.close();
            //DBConnection.getInstance().closeConnection();
            return prom;
        }
    }

    public static List<Promotion> getAllPersonalPromotions(String customerEmail) throws Exception {
        List<Promotion> promotionList = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getAllPersonalPromotions(stm, customerEmail);
        if (rs.first()) {
            rs.first();
            do {
                String promCode = rs.getString(2);
                Promotion promotion = getPromotion(promCode);
                if(!LocalDate.now().isAfter(promotion.getExpireDate())){
                    promotionList.add(promotion);
                }
            } while (rs.next());
            rs.close();
            stm.close();
            //DBConnection.getInstance().closeConnection();
        }
        return promotionList;
    }

    public static void deletePromotion(Promotion promotion) throws Exception{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        PromotionQueries.deletePromotion(stm, promotion.getCode());
        stm.close();
    }

    public static Promotion getPromotion(String promCode) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getPromotion(stm, promCode);
        if(!rs.first()){
            String message = "Promotion not available";
            throw new RecordNotFoundException(message);
        }else{
            Integer offValue = rs.getInt(2);
            String expireDate = rs.getString(3);
            String serviceName = rs.getString(4);
            String serviceShopName = rs.getString(5);
            Service service = ServiceDAO.getService(serviceShopName, serviceName);
            Promotion prom = new Promotion(promCode, offValue, dataFromString(expireDate), service);
            rs.close();
            stm.close();
            //DBConnection.getInstance().closeConnection();
            return prom;
        }
    }

    public static void insertPersonaPromotion(String customerE, String promoCode) throws Exception{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getPersonalPromotion(stm, customerE, promoCode);
        if (!rs.first()){
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            PromotionQueries.insertPersonalPromotion(stm, customerE, promoCode);
        } else System.out.println("Promotion " + promoCode + " already exists for " + customerE);
        rs.close();
        stm.close();
    }

    private static LocalDate dataFromString(String expireDate) {
        return LocalDate.parse(expireDate);
    }

    private static String stringFromData(LocalDate expireDate){
        return expireDate.toString();
    }


}

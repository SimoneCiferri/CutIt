package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.PromotionQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.RecordNotFoundException;
import cutit.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PromotionDAO {

    private PromotionDAO(){}

    public static void insertPromotion(Promotion promotion) throws DBConnectionException, SQLException, DuplicatedRecordException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getAllPromotion(stm, promotion.getService().getShopName());
        while (rs.next()) {
            String code = rs.getString(1);
            if (Objects.equals(promotion.getCode(), code)) {
                throw new DuplicatedRecordException(promotion.getService().getServiceName() + " already exists!");
            }
        }
        rs.close();
        stm.close();
        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        PromotionQueries.insertPromotion(stm, promotion.getCode(), promotion.getOffValue(), stringFromData(promotion.getExpireDate()), promotion.getService().getServiceName(), promotion.getService().getShopName());
        stm.close();
    }

    public static List<Promotion> getAllPromotion(String shopName) throws DBConnectionException, SQLException, RecordNotFoundException {
        List<Promotion> promotionsList = new ArrayList<>();
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
                String promServiceName = rs.getString(4);
                String promServiceShopName = rs.getString(5);
                Service service = ServiceDAO.getService(promServiceShopName, promServiceName);
                Promotion p = new Promotion(promotionCode, offValue, dataFromString(expireDate), service);
                if(!LocalDate.now().isAfter(p.getExpireDate())){
                    promotionsList.add(p);
                }
            } while (rs.next());
            rs.close();
            stm.close();
        }
        return promotionsList;
    }

    public static List<Promotion> getAllCustomerPromotion(String customerEmail) throws DBConnectionException, SQLException, RecordNotFoundException {
        List<Promotion> promotionsList = new ArrayList<>();
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
                String promServiceName = rs.getString("Service_Name");
                String promServiceShopName = rs.getString("Service_Shop_ShopName");
                Service service = ServiceDAO.getService(promServiceShopName, promServiceName);
                Promotion p = new Promotion(promotionCode, offValue, dataFromString(expireDate), service);
                if(!LocalDate.now().isAfter(p.getExpireDate())){
                    promotionsList.add(p);
                }
            } while (rs.next());
            rs.close();
            stm.close();
        }
        return promotionsList;
    }

    public static Promotion getPersonalPromotion(String customerEmail, String promCode) throws DBConnectionException, SQLException, RecordNotFoundException {
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
            return prom;
        }
    }

    public static List<Promotion> getAllPersonalPromotions(String customerEmail) throws DBConnectionException, SQLException, RecordNotFoundException {
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
        }
        return promotionList;
    }

    public static void deletePromotion(Promotion promotion) throws DBConnectionException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        PromotionQueries.deletePromotion(stm, promotion.getCode());
        stm.close();
    }

    public static Promotion getPromotion(String promCode) throws DBConnectionException, SQLException, RecordNotFoundException {
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
            return prom;
        }
    }

    public static void insertPersonalPromotion(String customerEmail, String promoCode) throws DBConnectionException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getPersonalPromotion(stm, customerEmail, promoCode);
        if (!rs.first()){
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            PromotionQueries.insertPersonalPromotion(stm, customerEmail, promoCode);
        }
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

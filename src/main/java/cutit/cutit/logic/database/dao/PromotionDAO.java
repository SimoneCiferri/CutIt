package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.PromotionQueries;
import cutit.cutit.logic.database.query.ServiceQueries;
import cutit.cutit.logic.model.Promotion;
import cutit.cutit.logic.model.Service;
import cutit.cutit.logic.model.Shop;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PromotionDAO {


    public static void insertPromotion(Promotion promotion, String serviceName, String shopName) throws Exception {
        Service service = ServiceDAO.getService(shopName, serviceName);
        promotion.setService(service);
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        PromotionQueries.insertPromotion(stm, promotion.getCode(), promotion.getOffValue(), stringFromData(promotion.getExpireDate()), promotion.getService().getServiceName(), promotion.getService().getShopName());
        if(stm != null){
            stm.close();
        }
    }

    public static List<Promotion> getAllPromotion(Shop shop) throws Exception {
        List<Promotion> promotionsList = new ArrayList<Promotion>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = PromotionQueries.getAllPromotion(stm, shop.getShopName());
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
            if (stm != null) {
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
        }
        return promotionsList;
    }

    public static void deletePromotion(Promotion promotion) throws Exception{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        PromotionQueries.deletePromotion(stm, promotion.getCode());
        if(stm != null){
            stm.close();
        }
    }

    private static LocalDate dataFromString(String expireDate) {
        return LocalDate.parse(expireDate);
    }

    private static String stringFromData(LocalDate expireDate){
        return expireDate.toString();
    }


}

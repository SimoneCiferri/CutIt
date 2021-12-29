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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PromotionDAO {


    public static void insertPromotion(Promotion promotion, Service service) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        //PromotionQueries.insertPromotion(stm, promotion.getCode(), service.getShopName() , promotion.getOffValue(), stringFromData(promotion.getExpireDate()), promotion.getPromName(), service.getServiceName(), service.getPrice(), service.getShopName());
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
                String promotionCode = rs.getString("Code");
                String promotionShop = rs.getString("Shop_ShopNAme");
                Integer offValue = rs.getInt("Off");
                String expireDate = rs.getString("ExpireDate");
                String promotionName = rs.getString("Name");
                String promService_Name = rs.getString("Service_Name");
                String promService_Price = rs.getString("Service_Price");
                String promService_ShopName = rs.getString("Service_Shop_ShopName");
                /*
                Promotion p = new Promotion(promotionCode, promotionName, offValue, dataFromString(expireDate), promService_Name);
                promotionsList.add(p);

                 */
            } while (rs.next());
            rs.close();
            if (stm != null) {
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
        }
        return promotionsList;
    }


    private static LocalDateTime dataFromString(String expireDate) {
        return LocalDateTime.parse(expireDate);
    }

    private static String stringFromData(LocalDateTime expireDate){
        return expireDate.toString();
    }


}

package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.bean.ManagePromotionBean;
import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.ServiceQueries;
import cutit.cutit.logic.database.query.UserQueries;
import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.Service;
import cutit.cutit.logic.model.Shop;
import cutit.cutit.logic.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {


    public static void insertService(Service service) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ServiceQueries.insertService(stm, service.getServiceName(), service.getPrice(), service.getShopName());
        if(stm != null){
            stm.close();
        }
    }

    public static List<Service> getALlServices(Shop shop) throws Exception {
        List<Service> servicesList = new ArrayList<Service>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ServiceQueries.getAllServices(stm, shop.getShopName());
        if (rs.first()) {
            rs.first();
            do {
                String serviceName = rs.getString(1);
                Float servicePrice = rs.getFloat(2);
                Service s = new Service(serviceName, servicePrice, shop.getShopName());
                servicesList.add(s);
            } while (rs.next());
            rs.close();
            if (stm != null) {
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
        }
        return servicesList;
    }

    public static void deleteService(Service service) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ServiceQueries.deleteService(stm, service.getServiceName() ,service.getShopName());
        if(stm != null){
            stm.close();
        }
    }

    public static Service getService(String shopName, String serviceName) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ServiceQueries.getService(stm, serviceName, shopName);
        rs.first();
        String servName = rs.getString(1);
        Float servPrice = rs.getFloat(2);
        String servShopName = rs.getString(3);
        Service s = new Service(servName, servPrice, servShopName);
        rs.close();
        if (stm != null) {
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
        return s;
    }

}

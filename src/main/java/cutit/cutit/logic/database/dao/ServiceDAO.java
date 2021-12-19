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


    private static ServiceDAO instance = null;

    private ServiceDAO(){}

    public static synchronized ServiceDAO getInstance(){
        if(instance == null){
            instance = new ServiceDAO();
        }
        return instance;
    }

    public void insertService(Service service) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ServiceQueries.insertService(stm, service.getServiceName(), service.getPrice(), service.getShopName());
        if(stm != null){
            stm.close();
        }
    }

    public List<Service> getALlServices(Shop shop) throws Exception {
        List<Service> servicesList = new ArrayList<Service>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ServiceQueries.getAllServices(stm, shop.getShopName());
        if (rs.first()) {
            rs.first();
            do {
                String serviceName = rs.getString("Name");
                Float servicePrice = rs.getFloat("Price");
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



    public void deleteService(Service service) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ServiceQueries.deleteService(stm, service.getServiceName() ,service.getShopName());
        if(stm != null){
            stm.close();
        }
    }

    public Service getService(ManagePromotionBean managePromotionBean) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ServiceQueries.getService(stm, managePromotionBean.getPromServiceName(), managePromotionBean.getPromShopName());
        rs.first();
        String serviceName = rs.getString("Name");
        Float servicePrice = rs.getFloat("Price");
        String shopName = rs.getString("Shop_ShopName");
        Service s = new Service(serviceName, servicePrice, shopName);
        rs.close();
        if (stm != null) {
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
        return s;
    }

}

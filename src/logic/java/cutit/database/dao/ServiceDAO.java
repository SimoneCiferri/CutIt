package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.ServiceQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.RecordNotFoundException;
import cutit.model.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceDAO {

    private ServiceDAO(){}

    public static void insertService(Service service) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ServiceQueries.getAllServices(stm, service.getShopName());
        while (rs.next()) {
            String name = rs.getString(1);
            if (Objects.equals(service.getServiceName(), name)) {
                throw new DuplicatedRecordException(service.getServiceName() + " already exists!");
            }
        }
        rs.close();
        stm.close();
        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ServiceQueries.insertService(stm, service.getServiceName(), service.getPrice(), service.getShopName());
        stm.close();
    }

    public static List<Service> getAllServices(String shopName) throws DBConnectionException, SQLException {
        List<Service> servicesList = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ServiceQueries.getAllServices(stm, shopName);
        if (rs.first()) {
            rs.first();
            do {
                String serviceName = rs.getString(1);
                Float servicePrice = rs.getFloat(2);
                Service s = new Service(serviceName, servicePrice, shopName);
                servicesList.add(s);
            } while (rs.next());
            rs.close();
            stm.close();
        }
        return servicesList;
    }

    public static void deleteService(Service service) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ServiceQueries.deleteService(stm, service.getServiceName() ,service.getShopName());
        stm.close();
    }

    public static Service getService(String shopName, String serviceName) throws DBConnectionException, SQLException, RecordNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ServiceQueries.getService(stm, serviceName, shopName);
        if(!rs.first()){
            String message = "Service not found";
            throw new RecordNotFoundException(message);
        }
        rs.first();
        String servName = rs.getString(1);
        Float servPrice = rs.getFloat(2);
        String servShopName = rs.getString(3);
        Service s = new Service(servName, servPrice, servShopName);
        rs.close();
        stm.close();
        return s;
    }

}

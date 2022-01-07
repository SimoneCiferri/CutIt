package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.AppointmentQueries;
import cutit.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public static List<Appointment> getAllCustomerAppointments(Customer customer) throws Exception {
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = AppointmentQueries.getAllCustomerAppointments(stm, customer.getUserID());
        if (rs.first()) {
            rs.first();
            do {
                String startTime = rs.getString(1);
                String endTime = rs.getString(2);
                String shopName = rs.getString(3);
                String serviceName = rs.getString(5);
                String serviceShopName = rs.getString(6);
                String promotionCode = rs.getString(7);
                Service service = ServiceDAO.getService(serviceShopName, serviceName);
                Shop shop = ShopDAO.getShopFromName(shopName);
                Appointment appointment = new Appointment(LocalDateTime.parse(startTime), LocalDateTime.parse(endTime), customer, service, shop);
                if(promotionCode != null){
                    Promotion prom = PromotionDAO.getPromotion(promotionCode);
                    appointment.setPromotion(prom);
                }
                appointmentList.add(appointment);
            } while (rs.next());
            rs.close();
            if (stm != null) {
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
        }
        return appointmentList;
    }

}

package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.AppointmentQueries;
import cutit.database.query.PromotionQueries;
import cutit.database.query.ServiceQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.RecordNotFoundException;
import cutit.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppointmentDAO {

    private AppointmentDAO(){}

    public static void insertAppointment(Appointment appointment) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = AppointmentQueries.getAllShopAppointments(stm, appointment.getShop().getShopName());
        while (rs.next()) {
            String appointmentStartTime = rs.getString(1);
            if (Objects.equals(appointment.getStartTime().toString(), appointmentStartTime)) {
                throw new DuplicatedRecordException("Appointment slot is not available!");
            }
        }
        rs.close();
        stm.close();

        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        if(appointment.getPromotion() != null){
            AppointmentQueries.insertAppointment(stm, stringFromData(appointment.getStartTime()), stringFromData(appointment.getEndTime()), appointment.getShop().getShopName(), appointment.getCustomer().getUserID(), appointment.getService().getServiceName(), appointment.getService().getPrice(), appointment.getPromotion().getCode());
        } else {
            AppointmentQueries.insertAppointment(stm, stringFromData(appointment.getStartTime()), stringFromData(appointment.getEndTime()), appointment.getShop().getShopName(), appointment.getCustomer().getUserID(), appointment.getService().getServiceName(), appointment.getService().getPrice());

        }
        stm.close();
    }



    public static List<Appointment> getAllCustomerAppointments(Customer customer) throws DBConnectionException, SQLException, RecordNotFoundException {
        List<Appointment> appointmentList = new ArrayList<>();
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
                String serviceShopName = rs.getString(7);
                String promotionCode = rs.getString(8);
                Service service = ServiceDAO.getService(serviceShopName, serviceName);
                Shop shop = ShopDAO.getShopLite(shopName); //chiamata Lite dello shop
                Appointment appointment = new Appointment(LocalDateTime.parse(startTime), LocalDateTime.parse(endTime), customer, service, shop);
                if(promotionCode != null){
                    Promotion prom = PromotionDAO.getPromotion(promotionCode);
                    appointment.setPromotion(prom);
                }
                appointmentList.add(appointment);
            } while (rs.next());
            rs.close();
            stm.close();
        }
        return appointmentList;
    }

    public static List<Appointment> getAllShopAppointments(Shop shop) throws DBConnectionException, SQLException, RecordNotFoundException {
        List<Appointment> appointmentList = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = AppointmentQueries.getAllShopAppointments(stm, shop.getShopName());
        if (rs.first()) {
            rs.first();
            do {
                String startTime = rs.getString(1);
                String endTime = rs.getString(2);
                String customerEmail = rs.getString(4);
                String serviceName = rs.getString(5);
                String serviceShopName = rs.getString(7);
                String promotionCode = rs.getString(8);
                Customer customer = CustomerDAO.getCustomer(customerEmail);
                Service service = ServiceDAO.getService(serviceShopName, serviceName);
                Appointment appointment = new Appointment(dataFromString(startTime), dataFromString(endTime), customer, service, shop);
                if(promotionCode != null){
                    Promotion prom = PromotionDAO.getPromotion(promotionCode);
                    appointment.setPromotion(prom);
                }
                appointmentList.add(appointment);
            } while (rs.next());
            rs.close();
            stm.close();
        }
        return appointmentList;
    }

    public static void deleteAppointment(String appointmentStartTime, String shopName) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        AppointmentQueries.deleteAppointment(stm, appointmentStartTime , shopName);
        stm.close();
    }

    private static LocalDateTime dataFromString(String expireDate) {
        return LocalDateTime.parse(expireDate);
    }

    private static String stringFromData(LocalDateTime expireDate){
        return expireDate.toString();
    }

}

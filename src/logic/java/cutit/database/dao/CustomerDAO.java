package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.CustomerQueries;
import cutit.database.query.UserQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.RecordNotFoundException;
import cutit.factory.AlertFactory;
import cutit.model.Appointment;
import cutit.model.Customer;
import cutit.model.Promotion;
import cutit.model.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class CustomerDAO {

    private CustomerDAO(){}

    public static void insertCustomer(Customer customer) throws Exception {
        UserDAO.insertNewUser(customer);
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        CustomerQueries.insertCustomer(stm, customer.getUserID(), customer.getBirthDate().toString(), customer.getGender(), customer.getName(), customer.getSurname());
        stm.close();
    }

    public static Customer getCustomer(User user) throws DBConnectionException, SQLException, RecordNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = CustomerQueries.getCustomer(stm, user.getUserID());
        if(!rs.first()){
            String message = "Customer not found";
            throw new RecordNotFoundException(message);
        }else{
            rs.first();
            String cEmail = rs.getString(1);
            String birthDate = rs.getString(2);
            String gender = rs.getString(3);
            String name = rs.getString(4);
            String surname = rs.getString(5);
            Customer customer = new Customer(cEmail, user.getPwd(), user.getRole(), name, surname, LocalDate.parse(birthDate), gender);
            List<Appointment> bookedApp = AppointmentDAO.getAllCustomerAppointments(customer);
            customer.setBookedAppointments(bookedApp);
            List<Promotion> allProm = PromotionDAO.getAllCustomerPromotion(customer.getUserID());
            customer.setPromotions(allProm);
            rs.close();
            stm.close();
            return customer;
        }
    }

    public static Customer getCustomer(String customerEmail) throws DBConnectionException, SQLException, RecordNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = CustomerQueries.getCustomer(stm, customerEmail);
        if(!rs.first()){
            String message = "Customer not found";
            throw new RecordNotFoundException(message);
        }else{
            rs.first();
            String cEmail = rs.getString(1);
            String name = rs.getString(4);
            String surname = rs.getString(5);
            Customer customer = new Customer();
            customer.setUserID(cEmail);
            customer.setName(name);
            customer.setSurname(surname);
            List<Appointment> bookedApp = AppointmentDAO.getAllCustomerAppointments(customer);
            customer.setBookedAppointments(bookedApp);
            List<Promotion> allProm = PromotionDAO.getAllCustomerPromotion(customer.getUserID());
            customer.setPromotions(allProm);
            rs.close();
            stm.close();
            return customer;
        }
    }

    public static void logout() throws DBConnectionException {
        DBConnection.getInstance().closeConnection();
    }

}

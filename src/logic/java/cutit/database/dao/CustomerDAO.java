package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.CustomerQueries;
import cutit.database.query.UserQueries;
import cutit.model.Appointment;
import cutit.model.Customer;
import cutit.model.Promotion;
import cutit.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class CustomerDAO {

    public static void insertCustomer(Customer customer) throws Exception {
        if(!UserDAO.checkIfUserExist(customer.getUserID())){
            UserDAO.insertNewUser(customer);
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            CustomerQueries.insertCustomer(stm, customer.getUserID(), customer.getBirthDate().toString(), customer.getGender(), customer.getName(), customer.getSurname());
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
        }
    }

    public static Customer getCustomer(User user) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = CustomerQueries.getCustomer(stm, user.getUserID());
        if(!rs.first()){
            Exception e = new Exception("No user Found matching with name: "+ user.getUserID());
            throw e;
        }else{
            String cEmail = rs.getString(1);
            String birthDate = rs.getString(2);
            String gender = rs.getString(3);
            String name = rs.getString(4);
            String surname = rs.getString(5);
            Customer customer = new Customer(cEmail, user.getPwd(), user.getRole(), name, surname, LocalDate.parse(birthDate), gender);
            List<Appointment> bookedApp = AppointmentDAO.getAllCustomerAppointments(customer);
            customer.setBookedAppointments(bookedApp);
            List<Promotion> allProm = PromotionDAO.getAllCustomerPromotion(customer);
            customer.setPromotions(allProm);
            rs.close();
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return customer;
        }
    }

    

}

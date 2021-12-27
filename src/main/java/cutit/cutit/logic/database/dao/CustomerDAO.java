package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.CustomerQueries;
import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDAO {

    private static CustomerDAO instance = null;

    private CustomerDAO(){

    }

    public static synchronized CustomerDAO getInstance(){
        if(instance == null){
            instance = new CustomerDAO();
        }
        return instance;
    }

    public void insertCustomer(Customer customer) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        CustomerQueries.insertCustomer(stm, customer.getUserID(), customer.getBirthDate(), customer.getGender(), customer.getName(), customer.getSurname());
        if(stm != null){
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
    }

    public Customer getCustomer(User user) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = CustomerQueries.getCustomer(stm, user.getUserID());
        if(!rs.first()){
            Exception e = new Exception("No user Found matching with name: "+ user.getUserID());
            throw e;
        }else{
            String cEmail = rs.getString(1);
            Integer age = rs.getInt(2);
            String name = rs.getString(3);
            String surname = rs.getString(4);
            String gender = rs.getString(5);
            Customer customer = new Customer(cEmail, user.getPwd(), user.getRole(), name, surname, age, gender);
            rs.close();
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return customer;
        }
    }

    

}

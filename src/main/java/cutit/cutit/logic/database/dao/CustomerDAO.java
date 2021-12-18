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
        CustomerQueries.insertCustomer(stm, customer.getUserID(), customer.getAge(), customer.getGender(), customer.getName(), customer.getSurname());
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
            Customer customer = new Customer(user.getUserID(), user.getPwd(), user.getRole(), rs.getString("CName"), rs.getString("CSurname"), rs.getInt("Age"), rs.getString("Gender"));
            if(stm != null){
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            return customer;
        }
    }

    

}

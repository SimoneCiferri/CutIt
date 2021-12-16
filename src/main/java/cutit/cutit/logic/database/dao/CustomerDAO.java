package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.CustomerQueries;
import cutit.cutit.logic.model.Customer;

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
        CustomerQueries.insertCustomer(stm, customer);
        if(stm != null){
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
    }

    

}

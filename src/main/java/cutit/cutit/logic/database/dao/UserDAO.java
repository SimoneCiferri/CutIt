package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.UserQueries;
import cutit.cutit.logic.model.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {

    private static UserDAO instance = null;

    private UserDAO(){}

    public static synchronized UserDAO getInstance(){
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    public void userLogin() throws Exception {
        Connection conn = conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        //int rs = UserQueries.insertCustomer(stm ,new Customer(4, "pellegrini", "pippo", "123@gmail.com"));
        if(stm != null){
            stm.close();
        }
        DBConnection.getInstance().closeConnection();
    }

    public void insertNewUser(Customer customer) throws Exception {
        Connection conn = conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        UserQueries.insertUser(stm, customer);
        if(stm != null){
            stm.close();
        }
        DBConnection.getInstance().closeConnection();
    }

}

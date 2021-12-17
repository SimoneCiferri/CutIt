package cutit.cutit.logic.database.dao;

import cutit.cutit.logic.database.DBConnection;
import cutit.cutit.logic.database.query.UserQueries;
import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.User;

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

    public void insertNewUser(User user) throws Exception {
        Connection conn = conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        UserQueries.insertUser(stm, user);
        if(stm != null){
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
    }

    public User userLogin(User user) throws Exception {
        Connection conn = conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = UserQueries.getUser(stm, user);
        if(!rs.first()){
            Exception e = new Exception("No user Found matching with name: "+ user.getUserID());
            throw e;
        }else{
            Integer role = rs.getInt("Role");
            user.setRole(role);
        }
        if(stm != null){
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
        return user;
    }

}

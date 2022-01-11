package cutit.database.dao;

import cutit.database.DBConnection;
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

public class UserDAO {

    public static Boolean checkIfUserExist(String userID) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = UserQueries.checkIfUserExists(stm, userID);
        if (!rs.first()) {
            Exception e = new Exception("Unable to execute query");
            throw e;
        } else {
            int exists = rs.getInt(1);
            rs.close();
            if (stm != null) {
                stm.close();
            }
            //DBConnection.getInstance().closeConnection();
            if(exists == 0){
                return false;
            }else{
                Exception e = new Exception("An account with the selected email already exists. Try another email.");
                throw e;
            }
        }
    }

    public static void insertNewUser(User user) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        UserQueries.insertUser(stm, user.getUserID(), user.getPwd(), user.getRole());
        if(stm != null){
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
    }

    public static void userLogin(User user) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = UserQueries.getUser(stm, user.getUserID(), user.getPwd());
        if(!rs.first()){
            Exception e = new Exception("No user Found matching with name: "+ user.getUserID());
            throw e;
        }else{
            Integer role = rs.getInt("Role");
            user.setRole(role);
        }
        rs.close();
        if(stm != null){
            stm.close();
        }
        //DBConnection.getInstance().closeConnection();
    }


}

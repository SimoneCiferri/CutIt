package cutit.database.dao;

import cutit.database.DBConnection;
import cutit.database.query.UserQueries;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.WrongCredentialsException;
import cutit.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private UserDAO(){}

    public static Boolean checkUser(String userID) throws DBConnectionException, SQLException, DuplicatedRecordException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = UserQueries.checkIfUserExists(stm, userID);
        rs.first();
        int exists = rs.getInt(1);
        rs.close();
        stm.close();
        if (exists == 0) {
            return false;
        } else {
            throw new DuplicatedRecordException("User with selected email already exists");
        }
    }

    public static void insertNewUser(User user) throws DBConnectionException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        UserQueries.insertUser(stm, user.getUserID(), user.getPwd(), user.getRole());
        stm.close();
    }

    public static void userLogin(User user) throws DBConnectionException, SQLException, WrongCredentialsException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = UserQueries.getUser(stm, user.getUserID(), user.getPwd());
        if (!rs.first()) {
            throw new WrongCredentialsException();
        } else {
            Integer role = rs.getInt("Role");
            user.setRole(role);
        }
        rs.close();
        stm.close();
    }


}

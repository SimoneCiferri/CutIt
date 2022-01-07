package cutit.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQueries {

    public static void insertUser(Statement stmt, String userID, String userPwd, Integer userRole) throws SQLException {
        String insertStatement = String.format("INSERT INTO user (UserID, Password, Role) VALUES ('%s', '%s', %d)", userID, userPwd, userRole);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet getUser(Statement stmt, String userID, String userPwd) throws SQLException {
        String sql = "SELECT * FROM user WHERE UserID = '" + userID + "' AND Password = '" + userPwd + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

}

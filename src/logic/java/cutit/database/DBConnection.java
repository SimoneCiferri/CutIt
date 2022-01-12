package cutit.database;

import cutit.exception.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CutItDB";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static Connection conn = null;

    private DBConnection(){
    }

    public static synchronized DBConnection getInstance(){
        if (instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws DBConnectionException {
        if(conn == null){
            try {
                Class.forName(DRIVER_CLASS_NAME);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch(ClassNotFoundException e) {
                String message = "Unable to load mysql Driver - " + e.getMessage();
                throw new DBConnectionException(message, e.getCause());
            }catch (SQLException e){
                String message = "Unable to get DB Connection - " + e.getMessage();
                throw new DBConnectionException(message, e.getCause());
            }
        }
        return conn;
    }

    public void closeConnection() throws DBConnectionException {
        try {
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            String message = "Unable to close DB Connection - " + e.getMessage();
            throw new DBConnectionException(message, e.getCause());
        }

    }
}

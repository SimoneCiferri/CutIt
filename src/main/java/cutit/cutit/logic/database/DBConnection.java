package cutit.cutit.logic.database;

import cutit.cutit.logic.log.LogWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/CutItDB";
    private Connection conn = null;

    private DBConnection(){
    }

    public static synchronized DBConnection getInstance(){
        if (instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {
        if(conn == null){
            try {
                Class.forName(DRIVER_CLASS_NAME);
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CutItDB", "root", "root");
            } catch(ClassNotFoundException e) {
                String message = "Unable to load Driver - " + e.getMessage();
                e.printStackTrace();
                throw new Exception(message, e.getCause());
            }catch (SQLException e){
                String message = "Unable to get Connection - " + e.getMessage();
                throw new Exception(message, e.getCause());
            }
        }
        return conn;
    }
}

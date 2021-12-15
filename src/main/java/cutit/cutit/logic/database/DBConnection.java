package cutit.cutit.logic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;

    private static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private DBConnection(){

        Connection conn = null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CutItDB");
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    };

    private static synchronized DBConnection getInstance(){

        if (instance == null){
            instance = new DBConnection();

            return instance;
        }

        //return placeholder to avoid error
        // cancel and modify
        return instance;
    }
}

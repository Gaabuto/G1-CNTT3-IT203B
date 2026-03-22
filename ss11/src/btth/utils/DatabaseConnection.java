package btth.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306?createDatabaseIfNotExist=true";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456";

    //mo ket noi
    public static Connection openConnection() {
        Connection con = null;
        try{
            Class.forName(DRIVER);

            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
        return con;
    }

    // dong ket noi


}

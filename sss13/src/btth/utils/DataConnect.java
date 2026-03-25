package btth.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/trading?createDatabaseIfNotExist=true";
    private final static String USER = "root";
    private final static String PASS = "123456";

    public  static Connection openConnection(){
        Connection conn = null;
        try {
                Class.forName(DRIVER);

                 conn = DriverManager.getConnection(URL,USER,PASS);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return conn;
    }
}

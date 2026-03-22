package btvn;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;


/*
vi no se phai chay 24/7, se gay de hong, de crash, giam hieu nang theo thoi gian...
 */
public class Bai1 {
    static void main(String[] args) {
    try(Connection conn = getConnection()){
        System.out.println("connected!");
    }catch(SQLException e){
        System.err.println(e.getMessage());
    }
    }
}

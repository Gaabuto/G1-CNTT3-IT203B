package btvn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class Bai2 {
    /*
    vi if chi check 1 dong, can while
     */

    static void main(String[] args) {
        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT medicine_name, stock FROM Pharmacy");
        ) {
            while (rs.next()) {
                System.out.println(
                        "Thuốc: " + rs.getString("medicine_name") +
                                " | Số lượng: " + rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }
}

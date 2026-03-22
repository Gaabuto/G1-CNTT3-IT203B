package btvn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.sql.DriverManager.getConnection;

public class Bai4 {
    static void main(String[] args) {
        // vi where or 1=1 thi luon true nen se hien thi het ra
        String sql = "SELECT * FROM Patients WHERE full_name = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, userInput);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("full_name"));
            }
        }
    }
}

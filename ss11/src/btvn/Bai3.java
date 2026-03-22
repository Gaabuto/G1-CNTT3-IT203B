package btvn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Bai3 {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Miu ban nhap id giuong");
        int inputId = sc.nextInt();
        String sql = "UPDATE Beds SET bed_status = ? WHERE bed_id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, "Occupied");
            ps.setInt(2, inputId);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Đã cập nhật giường bệnh thành công!");
            } else {
                System.out.println("Mã giường này không tồn tại!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

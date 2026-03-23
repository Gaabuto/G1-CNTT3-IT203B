package btvn;

import btth.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Bai2 {

    // vi may se tu dong chuan theo set nen khong can lo sai kieu du lieu
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sql = "UPDATE Vitals SET temperature = ? WHERE p_id = ?";

        try (Connection conn = DataConnect.connect()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            Double temp = sc.nextDouble();
            ps.setDouble(1, temp);
            Integer patientId = sc.nextInt();
            ps.setInt(2, patientId);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

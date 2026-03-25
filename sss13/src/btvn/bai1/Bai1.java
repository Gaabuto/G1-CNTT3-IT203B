package btvn.bai1;

import btth.utils.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bai1 {

    public static void capPhatThuoc(int medicineId, int patientId, int quantity) {

        Connection conn = null;

        try {
            conn = DataConnect.openConnection();
            conn.setAutoCommit(false);

            String sql1 = "update medicine_inventory set quantity = quantity - ? where medicine_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, quantity);
            ps1.setInt(2, medicineId);
            ps1.executeUpdate();

            String sql2 = "insert into prescription(patient_id, medicine_id, quantity) values(?, ?, ?)";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.setInt(3, quantity);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Cấp phát thuốc thành công");

        } catch (SQLException e) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Lỗi: " + e.getMessage());

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
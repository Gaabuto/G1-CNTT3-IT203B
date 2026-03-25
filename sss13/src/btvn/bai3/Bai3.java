package btvn.bai3;

import btth.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bai3 {

    public static void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {

        Connection conn = null;

        try {
            conn = DataConnect.openConnection();
            conn.setAutoCommit(false);

            String sqlCheck = "select balance from patient_wallet where patient_id = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, maBenhNhan);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                throw new Exception("Bệnh nhân không tồn tại");
            }

            double balance = rs.getDouble("balance");

            if (balance < tienVienPhi) {
                throw new Exception("Không đủ tiền thanh toán");
            }

            String sqlUpdateBalance = "update patient_wallet set balance = balance - ? where patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlUpdateBalance);
            ps1.setDouble(1, tienVienPhi);
            ps1.setInt(2, maBenhNhan);
            int row1 = ps1.executeUpdate();

            if (row1 == 0) {
                throw new Exception("Không cập nhật được số dư");
            }

            String sqlUpdateBed = "update beds set status = 'available' where patient_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlUpdateBed);
            ps2.setInt(1, maBenhNhan);
            int row2 = ps2.executeUpdate();

            if (row2 == 0) {
                throw new Exception("Không cập nhật được giường");
            }

            String sqlUpdatePatient = "update patients set status = 'discharged' where patient_id = ?";
            PreparedStatement ps3 = conn.prepareStatement(sqlUpdatePatient);
            ps3.setInt(1, maBenhNhan);
            int row3 = ps3.executeUpdate();

            if (row3 == 0) {
                throw new Exception("Không cập nhật được bệnh nhân");
            }

            conn.commit();
            System.out.println("Xuất viện và thanh toán thành công");

        } catch (Exception e) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Thất bại: " + e.getMessage());

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
package btvn.bai2;

import btth.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Bai2 {
    //vi no se chi print ra loi thoi chu khong giai quyet duoc van de, no se chi thong bao
    public static void thanhToanVienPhi(int patientId, int invoiceId, double amount) {

        Connection conn = null;

        try {
            conn = DataConnect.openConnection();

            conn.setAutoCommit(false);

            String sqlDeductWallet = "update patient_wallet set balance = balance - ? where patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlDeductWallet);
            ps1.setDouble(1, amount);
            ps1.setInt(2, patientId);
            ps1.executeUpdate();

            String sqlUpdateInvoice = "update invoices set status = 'paid' where invoice_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice);
            ps2.setInt(1, invoiceId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Thanh toán hoàn tất!");

        } catch (SQLException e) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Lỗi hệ thống! Đã rollback. Chi tiết: " + e.getMessage());

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
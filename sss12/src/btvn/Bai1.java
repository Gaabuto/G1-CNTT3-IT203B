package btvn;

import btth.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Bai1 {
    // vi may se phai doc code roi gan gia tri chu khong phai thuc hien lenh code
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        try ((Connection conn = DataConnect.connect()){
            String code = sc.nextLine();
            PreparedStatement ps = null;
            ps.setString(1, code);
            String pass = sc.nextLine();
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công!");
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

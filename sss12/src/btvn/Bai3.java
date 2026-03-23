package btvn;

import btth.utils.DataConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Bai3 {
    // vi may tinh khong the phan biet in out nen can dang ky truoc
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try(Connection conn = DataConnect.connect()){
            CallableStatement call = con.prepareCall(sql);
            call.setInt(1, 505);

            call.registerOutParameter(2, Types.DOUBLE);

            call.execute();

            double cost = call.getDouble(2);

            System.out.println("Chi phí phẫu thuật: " + cost);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

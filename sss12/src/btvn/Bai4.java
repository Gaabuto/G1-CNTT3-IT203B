package btvn;

import btth.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Bai4 {
    // vi se phai chay 1000 lan, cu the can parse x1000 lan compile x1000
    static void main(String[] args) {
        List<TestResult> list = new ArrayList<>();
        list.add(new TestResult("A"));
        list.add(new TestResult("B"));
        list.add(new TestResult("C"));

        String sql = "INSERT INTO results(data) VALUES(?)";
        try(Connection conn = DataConnect.connect()){
            PreparedStatement ps = con.prepareStatement(sql);
            for (TestResult tr : list) {
                ps.setString(1, tr.getData());// nạp tham số
                ps.executeUpdate(); // thực thi
            }

            System.out.println("Nạp thành công");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}

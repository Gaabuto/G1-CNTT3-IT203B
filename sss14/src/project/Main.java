package project;

import project.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = DataConnect.openConnection();
        try{
            conn.setAutoCommit(false);
            String sqlCheck = """
                    select Balance from Accounts where AccountId like ?;
                    """;
            String sqlTransfer = """
                    update Accounts set Balance = Balance - ? where AccountId like ?;
                    """;
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            PreparedStatement psTransfer = conn.prepareStatement(sqlTransfer);
            System.out.println("Moi nhap id tai khoan cua ban");
            String idAccount = sc.nextLine();
            System.out.println("Moi nhap id tai khoan ban muon chuyen");
            String idToTransfer = sc.nextLine();
            System.out.println("Moi nhap so tien can chuyen");
            Double money = sc.nextDouble();

            psCheck.setString(1, idAccount);
            var rs = psCheck.executeQuery();
            if(rs.next()){
                Double balance = rs.getDouble("Balance");
                if(balance < money){
                    System.out.println("So du khong du de chuyen");
                }else{
                    psTransfer.setDouble(1, money);
                    psTransfer.setString(2, idAccount);
                    psTransfer.executeUpdate();
                    psTransfer.setDouble(1, -money);
                    psTransfer.setString(2, idToTransfer);
                    psTransfer.executeUpdate();
                    conn.commit();
                    System.out.println("Chuyen tien thanh cong");
                }
            }else{
                System.out.println("Tai khoan khong ton tai");
            }

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}

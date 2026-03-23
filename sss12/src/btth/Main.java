package btth;

import btth.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try(Connection con = DataConnect.connect()){

            String sql = "INSERT INTO favourite_team(team_name,price,owner) VALUES(?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);


            for (int i = 0; i < 3; i++) {
                System.out.println("Moi ban doi bong ban yeu thich");
                prepare.setString(1,sc.nextLine());
                System.out.println("Nhao vao gia tri doi hinh");
                prepare.setDouble(2,Double.parseDouble(sc.nextLine()));
                System.out.println("Nhap ten chi tich");
                prepare.setString(3,sc.nextLine());
            }


            int afftectRow = prepare.executeUpdate();
            if(afftectRow>0){
                System.out.println("Thank you! Enjoy the favourite team");
            }else{
                System.err.println("That team is not available");
            }

            prepare.clearParameters();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}

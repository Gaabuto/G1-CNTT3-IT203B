package btth;

import btth.utils.DataConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class HandleRankTeam {
    static void main(String[] args) {
        try(Connection conn = DataConnect.connect()){

            CallableStatement call = conn.prepareCall("{ call proc_update_rank_team(?,?)}");

            call.setInt(1,1);
            call.setInt(2,5);

            int affRow = call.executeUpdate();
            if(affRow>0){
                System.out.println("Update successful");
            }else{
                System.err.println("Update failed");
            }

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}

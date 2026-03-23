package btth.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class HandleSearchRank {
    static void main(String[] args) {
        try(Connection conn = DataConnect.connect()){

            CallableStatement call = conn.prepareCall("{call HandleSearchRank(?,?)}");
            call.setInt(1, 5);
            call.registerOutParameter(2, Types.VARCHAR);
            call.executeQuery();
            String name = call.getString(2);
            System.out.println("Doi bong toi yeu" + name);
        }catch(SQLException e){
            System.err.println("HandleSearchRank SQLException");
        }
    }
}

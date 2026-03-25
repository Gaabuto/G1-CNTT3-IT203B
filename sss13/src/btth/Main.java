package btth;

import btth.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    static void main(String[] args) throws SQLException {
        Connection conn = DataConnect.openConnection();
        try{
            conn.setAutoCommit(false);
            String sqlBuy = """
                    update accounts set balance = balance - ? where id = ?;
                    """;
            String sqlSell = """
                    update accounts set balance = balance + ? where id = ?;
                    """;
            String sqlTransfers = """
                    insert into transfers (type,amount,account_id) values (?,?,?);
                    """;

            int accountID = 1;
            int amountBuy= 2;
            int amountSell= 1;

            PreparedStatement prepareBuy = conn.prepareStatement(sqlBuy);
            prepareBuy.setDouble(1, amountBuy);
            prepareBuy.setDouble(2, amountSell);


            PreparedStatement prepareSell = conn.prepareStatement(sqlSell);
            prepareSell.setDouble(1, amountSell);
            prepareSell.setDouble(2, accountID);


            PreparedStatement prepareTransfer = conn.prepareStatement(sqlTransfers);
            prepareTransfer.setString(1,"BUY");
            prepareTransfer.setDouble(2,amountBuy);
            prepareTransfer.setDouble(3,accountID);
            prepareTransfer.executeUpdate();

            prepareTransfer.clearParameters();
            prepareTransfer.setString(1,"SELL");
            prepareTransfer.setDouble(2,amountSell);
            prepareTransfer.setDouble(3,accountID);
            prepareTransfer.executeUpdate();

            prepareBuy.executeUpdate();
            prepareBuy.executeUpdate();
            prepareTransfer.executeUpdate();
            prepareTransfer.executeUpdate();

            conn.commit();
        }catch(Exception e){
            conn.rollback();
            System.err.println(e.getMessage());
        }finally{
            conn.close();
        }
    }
}

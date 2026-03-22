package btth.persistence;

import btth.utils.DatabaseConnection;

import java.sql.Statement;

public class Main {
    static void main(String[] args) {
        System.out.println("Chuan bi ket noi");

        DatabaseConnection.openConnection();
        Statement statement = con.createStatement;


        System.out.println("Da ket noi thanh cong");
    }
}
package btvn.bai3;

import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        String password1 = "";
        String password2 = "1234567";

        Auth user1 = ()->password1;
        Auth user2 = ()->password2;

        System.out.println("Password 1: "+(user1.isAuthenticated()?"Hợp lệ":"Không hợp lệ"));
        System.out.println("Password 2: "+(user2.isAuthenticated()?"Hợp lệ":"Không hợp lệ"));

        System.out.println(Auth.encrypt(password2));
    }
}
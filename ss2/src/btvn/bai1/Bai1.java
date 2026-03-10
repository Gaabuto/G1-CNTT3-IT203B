package btvn.bai1;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Nhập tên: ");
        String name = sc.nextLine();
        System.out.printf("Nhập vai trò: ");
        String role = sc.nextLine();

        User user = new User(name, role);

        Predicate<User> checkRole =u ->u.getRole().equalsIgnoreCase("Admin");
        System.out.println(checkRole.test(user) ? "Đây là Admin" : "Đây không là admin");


        Function<User, String> showUser = u -> u.getName();
        System.out.println(showUser.apply(user));

        Consumer<User> printUser = u -> System.out.println(u.getName());
        printUser.accept(user);

        User user1 = new User("Khanh", "User");
        System.out.println(showUser.apply(user1));
        System.out.println(checkRole.test(user1));
    }
}
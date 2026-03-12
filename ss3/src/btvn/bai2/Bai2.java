package btvn.bai2;

import java.util.ArrayList;

public class Bai2 {
    static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
            User user1 = new User("alice@gmal.com");
            User user2 = new User("bob@yahoo.com");
            User user3 = new User("Charlie@gmail.com");
            users.add(user1);
            users.add(user2);
            users.add(user3);
            users.stream()
                    .filter(user -> user.email().toLowerCase().endsWith("@gmail.com"))
                    .forEach(user -> System.out.println(user.email()));

    }
}

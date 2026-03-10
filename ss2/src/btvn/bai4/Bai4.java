package btvn.bai4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Bai4 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Thành"));
        users.add(new User("Dũng"));
        users.add(new User("Tú"));

        users
                .stream()
                .map(User::getName)
                .forEach(System.out::println);


        Function<String, User> findUser =User::new;
        User user = findUser.apply("Anh");
    }
}
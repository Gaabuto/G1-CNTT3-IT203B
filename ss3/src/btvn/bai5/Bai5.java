package btvn.bai5;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

    record User(String username) {}

public class Bai5 {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>(
                List.of(
                        new User("Tú"),
                        new User("Huy"),
                        new User("Dũng"),
                        new User("An"),
                        new User("Thành"),
                        new User("Long")

                )
        );

        list
                .stream()
                .sorted(Comparator.comparing((User u) -> u.username().length()).reversed())
                .limit(3)
                .forEach(u -> System.out.println(u.username()));
    }
}
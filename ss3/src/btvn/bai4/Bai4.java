package btvn.bai4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

record User(String name){}

public class Bai4 {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>(
                List.of(
                        new User("Tú"),
                        new User("An"),
                        new User("Tú"),
                        new User("Dũng"),
                        new User("Thành")
                )
        );

        Set<User> users = new HashSet<>(list);
        users.forEach(System.out::println);
    }
}

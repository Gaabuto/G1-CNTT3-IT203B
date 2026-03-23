package btvn.bai3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    List<User> users = new ArrayList<>(
            List.of(
                    new User("Logan"),
                    new User("Alice"),
                    new User("Paul")
            )
    );

    Optional<User> findUserByUsername(String username){
        return users
                .stream()
                .filter((user)-> user.getUserName().equals(username))
                .findFirst();
    }
}
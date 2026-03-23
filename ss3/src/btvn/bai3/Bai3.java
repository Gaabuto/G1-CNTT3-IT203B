package btvn.bai3;

public class Bai3 {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        String result = userRepository.findUserByUsername("Alice")
                .map((u) -> "Welcome "+u.getUserName())
                .orElse("Guest Login");
        System.out.println(result);
    }
}

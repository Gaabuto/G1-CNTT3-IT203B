package btvn.bt1;

public class UserValidator {
    public boolean isValidUsername(String username) {

        if (username == null) {
            return false;
        }

        if (username.length() < 5 || username.length() > 15) {
            return false;
        }
        if (username.contains(" ")) {
            return false;
        }


        return username.matches("[a-zA-Z0-9]+");
    }
}
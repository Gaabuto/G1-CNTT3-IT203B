package btvn.bai2;

public class Bai2 {
    static void main(String[] args) {
        PasswordValidator passwordValidator = password -> password.length() > 5;

        System.out.println(passwordValidator.validate("aaaa"));
        System.out.println(passwordValidator.validate("aaaaaaaaa"));
    }
}

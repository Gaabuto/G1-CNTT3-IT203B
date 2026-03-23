package btvn.bai2;



import java.util.ArrayList;
import java.util.List;

public class Bai2 {
    public static void main(String[] args) {
        List<String> emails = new ArrayList<>();
        emails.add("alice@gmail.com");
        emails.add("bob@yahoo.com");
        emails.add("charlie@gmail.com");

        emails
                .stream()
                .filter((email)-> email.endsWith("@gmail.com"))
                .forEach(System.out::println);
    }
}


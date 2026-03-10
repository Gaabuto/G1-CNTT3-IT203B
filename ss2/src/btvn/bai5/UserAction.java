package btvn.bai5;

public interface UserAction {
    default void logActivity(String activity) {
        System.out.println("User activity: " + activity);
    }
}

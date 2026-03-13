package test.bt2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void testAge18IsValid() {
        int age = 18;
        boolean result = userService.checkRegistrationAge(age);
        assertEquals(true, result);
    }

    @Test
    void testAge17IsInvalid() {
        int age = 17;
        boolean result = userService.checkRegistrationAge(age);
        assertEquals(false, result);
    }

    @Test
    void testNegativeAgeThrowsException() {
        int age = -1;
        assertThrows(IllegalArgumentException.class, () -> {
            userService.checkRegistrationAge(age);
        });
    }
}
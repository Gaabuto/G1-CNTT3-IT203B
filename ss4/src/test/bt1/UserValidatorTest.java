package test.bt1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UserValidatorTest {

    private final UserValidator validator = new UserValidator();
    @Test
    void testValidUsername() {
        String username = "user123";

        boolean result = validator.isValidUsername(username);
        assertTrue(result);
    }
    @Test
    void testUsernameTooShort() {
        String username = "abc";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }
    @Test
    void testUsernameContainsSpace() {
        String username = "user name";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }
}
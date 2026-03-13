package test.bt4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordServiceTest {

    private PasswordService service;

    @BeforeEach
    void setUp() {
        service = new PasswordService();
    }

    @Test
    void testStrongPassword() {
        String result = service.evaluatePasswordStrength("Abc123!@");

        assertAll(
                () -> assertEquals("Mạnh", result)
        );
    }

    @Test
    void testMissingUppercase() {
        String result = service.evaluatePasswordStrength("abc123!@");

        assertAll(
                () -> assertEquals("Trung bình", result)
        );
    }

    @Test
    void testMissingLowercase() {
        String result = service.evaluatePasswordStrength("ABC123!@");

        assertAll(
                () -> assertEquals("Trung bình", result)
        );
    }

    @Test
    void testMissingNumber() {
        String result = service.evaluatePasswordStrength("Abcdef!@");

        assertAll(
                () -> assertEquals("Trung bình", result)
        );
    }

    @Test
    void testMissingSpecialCharacter() {
        String result = service.evaluatePasswordStrength("Abc12345");

        assertAll(
                () -> assertEquals("Trung bình", result)
        );
    }

    @Test
    void testPasswordTooShort() {
        String result = service.evaluatePasswordStrength("Ab1!");

        assertAll(
                () -> assertEquals("Yếu", result)
        );
    }

    @Test
    void testOnlyLowercase() {
        String result = service.evaluatePasswordStrength("password");

        assertAll(
                () -> assertEquals("Yếu", result)
        );
    }

    @Test
    void testUppercaseAndNumberOnly() {
        String result = service.evaluatePasswordStrength("ABC12345");

        assertAll(
                () -> assertEquals("Yếu", result)
        );
    }
}
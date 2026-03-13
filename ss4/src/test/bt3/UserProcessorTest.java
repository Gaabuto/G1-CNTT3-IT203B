package test.bt3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProcessorTest {

    private UserProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }

    @Test
    void testValidEmailFormat() {
        String email = "user@gmail.com";
        String result = processor.processEmail(email);
        assertEquals("user@gmail.com", result);
    }

    @Test
    void testEmailWithoutAtSymbol() {
        String email = "usergmail.com";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }

    @Test
    void testEmailWithoutDomain() {
        String email = "user@";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }

    @Test
    void testEmailNormalizationToLowercase() {
        String email = "Example@Gmail.com";
        String result = processor.processEmail(email);
        assertEquals("example@gmail.com", result);
    }
}
package test.bt5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PermissionServiceTest {

    private PermissionService service;
    private User admin;
    private User moderator;
    private User user;

    @BeforeEach
    void setUp() {
        service = new PermissionService();
        admin = new User(Role.ADMIN);
        moderator = new User(Role.MODERATOR);
        user = new User(Role.USER);
    }

    @AfterEach
    void tearDown() {
        admin = null;
        moderator = null;
        user = null;
    }

    @Test
    void testAdminPermissions() {
        assertAll(
                () -> assertTrue(service.canPerformAction(admin, Action.DELETE_USER)),
                () -> assertTrue(service.canPerformAction(admin, Action.LOCK_USER)),
                () -> assertTrue(service.canPerformAction(admin, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testModeratorPermissions() {
        assertAll(
                () -> assertFalse(service.canPerformAction(moderator, Action.DELETE_USER)),
                () -> assertTrue(service.canPerformAction(moderator, Action.LOCK_USER)),
                () -> assertTrue(service.canPerformAction(moderator, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testUserPermissions() {
        assertAll(
                () -> assertFalse(service.canPerformAction(user, Action.DELETE_USER)),
                () -> assertFalse(service.canPerformAction(user, Action.LOCK_USER)),
                () -> assertTrue(service.canPerformAction(user, Action.VIEW_PROFILE))
        );
    }
}
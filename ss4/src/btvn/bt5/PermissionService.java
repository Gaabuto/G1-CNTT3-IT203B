package btvn.bt5;

public class PermissionService {

    public boolean canPerformAction(User user, Action action) {

        Role role = user.getRole();

        if (role == Role.ADMIN) {
            return true;
        }

        if (role == Role.MODERATOR) {
            return action == Action.LOCK_USER || action == Action.VIEW_PROFILE;
        }

        if (role == Role.USER) {
            return action == Action.VIEW_PROFILE;
        }

        return false;
    }
}

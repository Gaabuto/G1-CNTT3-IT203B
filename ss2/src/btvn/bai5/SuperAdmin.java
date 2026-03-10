package btvn.bai5;

class SuperAdmin implements UserAction, AdminActions {

    @Override
    public void logActivity(String activity) {
        UserAction.super.logActivity(activity);
        AdminActions.super.logActivity(activity);
    }

}
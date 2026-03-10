package btvn.bai3;

public interface Auth {
        String getPassword();

        default boolean isAuthenticated(){
            return getPassword() != null && !getPassword().isEmpty();
        }

        static String encrypt(String hidePassword){
            return "Đã mã hóa: "+ hidePassword;
        }
    }

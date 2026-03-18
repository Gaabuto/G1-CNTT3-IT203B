package bai01;

public class HardwareConnection {
    private static HardwareConnection instance;
    public static HardwareConnection getInstance(){
        if(instance == null){
            instance = new HardwareConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Đã kết nối tới phần cứng!");
    }

    public void disconnect() {
        System.out.println("Đã ngắt kết nối phần cứng!");
    }
}

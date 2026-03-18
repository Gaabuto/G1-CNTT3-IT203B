package bai05;

public class Fan implements Observer {
    private String speed = "Tắt";

    public void setLow() {
        speed = "Thấp";
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    public void setHigh() {
        speed = "Mạnh";
        System.out.println("Quạt: Chạy tốc độ mạnh");
    }

    public void update(int temperature) {
        if (temperature > 30) {
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
            speed = "Mạnh";
        }
    }
}

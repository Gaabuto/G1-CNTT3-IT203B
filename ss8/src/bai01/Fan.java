package bai01;

public class Fan implements Device {

    @Override
    public void turnOn() {
        System.out.println("Quạt BẬT");
    }

    @Override
    public void turnOff() {
        System.out.println("Quạt TẮT");
    }
}

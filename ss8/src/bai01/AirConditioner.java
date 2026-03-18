package bai01;

public class AirConditioner implements Device {

    @Override
    public void turnOn() {
        System.out.println("Điều hòa BẬT");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa TẮT");
    }
}

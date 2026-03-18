package bai01;

public class Light implements Device {

    @Override
    public void turnOn() {
        System.out.println("Đèn BẬT");
    }

    @Override
    public void turnOff() {
        System.out.println("Đèn TẮT");
    }
}

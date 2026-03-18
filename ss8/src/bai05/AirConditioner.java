package bai05;

public class AirConditioner implements Observer {
    private int temperature = 25;

    public void setTemperature(int temp) {
        temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    public void update(int temperature) {
        System.out.println("Điều hòa: Nhiệt độ = " + this.temperature);
    }
}

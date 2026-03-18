package bai05;

public class SleepModeCommand implements Command {
    private Light light;
    private Fan fan;
    private AirConditioner ac;

    public SleepModeCommand(Light light, Fan fan, AirConditioner ac) {
        this.light = light;
        this.fan = fan;
        this.ac = ac;
    }

    public void execute() {
        System.out.println("\nSleepMode: Tắt đèn");
        System.out.println("SleepMode: Điều hòa set 28°C");
        System.out.println("SleepMode: Quạt tốc độ thấp");

        light.off();
        ac.setTemperature(28);
        fan.setLow();
    }
}

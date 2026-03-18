package bai01;

public class AirConditionerFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        return new AirConditioner();
    }
}

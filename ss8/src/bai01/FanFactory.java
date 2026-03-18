package bai01;

public class FanFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        return new Fan();
    }
}

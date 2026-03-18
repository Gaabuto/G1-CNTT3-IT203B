package btth.Pattern;
import Ex9.BTTH.Entity.TrafficLight;

public interface TrafficLightState {
    void handle(TrafficLight light);
    String getColor();
}
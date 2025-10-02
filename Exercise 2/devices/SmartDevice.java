package devices;

public interface SmartDevice {
    int getId();
    String getStatus();
    void turnOn();
    void turnOff();
    boolean isOn();
}

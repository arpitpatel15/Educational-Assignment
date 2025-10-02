
interface Device {
    void turnOn();
    void turnOff();
}

class TV implements Device {
    public void turnOn() { System.out.println("TV is ON"); }
    public void turnOff() { System.out.println("TV is OFF"); }
}

class Radio implements Device {
    public void turnOn() { System.out.println("Radio is ON"); }
    public void turnOff() { System.out.println("Radio is OFF"); }
}

abstract class RemoteControl {
    protected Device device;
    public RemoteControl(Device device) { this.device = device; }
    abstract void pressOn();
    abstract void pressOff();
}

class BasicRemote extends RemoteControl {
    public BasicRemote(Device device) { super(device); }
    public void pressOn() { device.turnOn(); }
    public void pressOff() { device.turnOff(); }
}

public class BridgePattern {
    public static void main(String[] args) {
        RemoteControl tvRemote = new BasicRemote(new TV());
        tvRemote.pressOn();
        tvRemote.pressOff();

        RemoteControl radioRemote = new BasicRemote(new Radio());
        radioRemote.pressOn();
        radioRemote.pressOff();
    }
}

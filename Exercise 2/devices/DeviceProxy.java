package devices;

public class DeviceProxy implements SmartDevice {
    private SmartDevice realDevice;
    private boolean authorized;

    public DeviceProxy(SmartDevice device, boolean authorized) {
        this.realDevice = device;
        this.authorized = authorized;
    }

    @Override public int getId() { return realDevice.getId(); }

    @Override
    public String getStatus() {
        return authorized ? realDevice.getStatus() : "Access Denied to Device " + getId();
    }

    @Override
    public void turnOn() {
        if (authorized) realDevice.turnOn();
        else System.out.println("Unauthorized access to Device " + getId());
    }

    @Override
    public void turnOff() {
        if (authorized) realDevice.turnOff();
        else System.out.println("Unauthorized access to Device " + getId());
    }

    @Override public boolean isOn() { return realDevice.isOn(); }

    public void setAuthorized(boolean auth) { this.authorized = auth; }
    public boolean isAuthorized() { return authorized; }
}

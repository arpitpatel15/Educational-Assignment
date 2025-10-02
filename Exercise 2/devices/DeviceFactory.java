package devices;

public class DeviceFactory {
    public static SmartDevice createDevice(int type, int id) {
        switch(type) {
            case 0: return new Light(id);
            case 1: return new Thermostat(id);
            case 2: return new DoorLock(id);
            default: throw new IllegalArgumentException("Invalid device type code! Use 0=Light, 1=Thermostat, 2=Door.");
        }
    }
}

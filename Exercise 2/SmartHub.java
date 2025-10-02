import devices.*;
import triggers.*;
import java.util.*;

public class SmartHub {
    private Map<Integer, SmartDevice> devices = new HashMap<>();
    private List<observer.Observer> observers = new ArrayList<>();
    private List<String> schedules = new ArrayList<>();
    private List<Trigger> triggers = new ArrayList<>();

    // Observer methods
    public void addObserver(observer.Observer observer) { observers.add(observer); }
    public void notifyObservers(String message) {
        for (observer.Observer o : observers) o.update(message);
    }

    // Device management
    public void addDevice(SmartDevice device) {
        if (devices.containsKey(device.getId())) {
            System.out.println("Error: Device ID " + device.getId() + " already exists!");
            return;
        }
        devices.put(device.getId(), device);
        notifyObservers("Device " + device.getId() + " added.");
    }

    public void removeDevice(int id) {
        if (!devices.containsKey(id)) {
            System.out.println("Error: Device ID " + id + " not found!");
            return;
        }
        devices.remove(id);
        notifyObservers("Device " + id + " removed.");
    }

    // Control methods
    public void turnOn(int id) {
        SmartDevice d = devices.get(id);
        if (d != null) {
            d.turnOn();
            notifyObservers("Device " + id + " ON command processed.");
        } else System.out.println("Error: Device ID " + id + " not found!");
    }

    public void turnOff(int id) {
        SmartDevice d = devices.get(id);
        if (d != null) {
            d.turnOff();
            notifyObservers("Device " + id + " OFF command processed.");
        } else System.out.println("Error: Device ID " + id + " not found!");
    }

    // Scheduling
    public void setSchedule(int id, String time, String command) {
        if (!devices.containsKey(id)) {
            System.out.println("Error: Device ID " + id + " not found!");
            return;
        }
        schedules.add("{device: " + id + ", time: " + time + ", command: " + command + "}");
        notifyObservers("Schedule added for Device " + id + " at " + time);
    }

    // Triggers
    public void addTrigger(String condition, String action) {
        triggers.add(new Trigger(condition, action));
        notifyObservers("Trigger added: " + condition + " => " + action);
    }

    // Authorization
    public void setAuthorization(int id, boolean auth) {
        SmartDevice d = devices.get(id);
        if (d instanceof DeviceProxy) {
            ((DeviceProxy) d).setAuthorized(auth);
            System.out.println("Authorization for Device " + id + " set to " + auth);
        } else {
            System.out.println("Device " + id + " is not protected (not a proxy).");
        }
    }

    // Reports
    public void viewStatus() {
        if (devices.isEmpty()) System.out.println("No devices in the system.");
        for (SmartDevice d : devices.values()) System.out.println(d.getStatus());
    }

    public void viewSchedules() {
        if (schedules.isEmpty()) System.out.println("No schedules set.");
        else System.out.println("Scheduled Tasks: " + schedules);
    }

    public void viewTriggers() {
        if (triggers.isEmpty()) System.out.println("No triggers set.");
        else System.out.println("Automated Triggers: " + triggers);
    }
}

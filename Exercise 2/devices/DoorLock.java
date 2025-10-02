package devices;

public class DoorLock implements SmartDevice {
    private int id;
    private boolean isLocked = true;

    public DoorLock(int id) { this.id = id; }
    @Override public int getId() { return id; }

    @Override
    public String getStatus() { return "Door " + id + " is " + (isLocked ? "Locked" : "Unlocked"); }

    @Override
    public void turnOn() {
        if (!isLocked) System.out.println("Door " + id + " is already Unlocked.");
        else { isLocked = false; System.out.println("Door " + id + " Unlocked."); }
    }

    @Override
    public void turnOff() {
        if (isLocked) System.out.println("Door " + id + " is already Locked.");
        else { isLocked = true; System.out.println("Door " + id + " Locked."); }
    }

    @Override public boolean isOn() { return !isLocked; }
}

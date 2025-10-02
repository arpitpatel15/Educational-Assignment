package devices;

public class Light implements SmartDevice {
    private int id;
    private boolean isOn = false;

    public Light(int id) { this.id = id; }

    @Override public int getId() { return id; }

    @Override
    public String getStatus() { return "Light " + id + " is " + (isOn ? "On" : "Off"); }

    @Override
    public void turnOn() {
        if (isOn) System.out.println("Light " + id + " is already ON.");
        else { isOn = true; System.out.println("Light " + id + " turned ON."); }
    }

    @Override
    public void turnOff() {
        if (!isOn) System.out.println("Light " + id + " is already OFF.");
        else { isOn = false; System.out.println("Light " + id + " turned OFF."); }
    }

    @Override public boolean isOn() { return isOn; }
}

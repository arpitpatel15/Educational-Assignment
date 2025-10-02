package devices;

public class Thermostat implements SmartDevice {
    private int id;
    private int temperature = 70;
    private boolean active = false;

    public Thermostat(int id) { this.id = id; }
    @Override public int getId() { return id; }

    @Override
    public String getStatus() {
        return "Thermostat " + id + " is set to " + temperature + " degrees (" + (active ? "Active" : "Idle") + ")";
    }

    @Override
    public void turnOn() {
        if (active) System.out.println("Thermostat " + id + " is already active.");
        else { active = true; temperature += 5; System.out.println("Thermostat " + id + " activated. Temp set to " + temperature); }
    }

    @Override
    public void turnOff() {
        if (!active) System.out.println("Thermostat " + id + " is already idle.");
        else { active = false; temperature -= 5; System.out.println("Thermostat " + id + " turned OFF. Temp set to " + temperature); }
    }

    @Override public boolean isOn() { return active; }

    public void setTemperature(int temp) { this.temperature = temp; }
    public int getTemperature() { return this.temperature; }
}

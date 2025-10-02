import devices.*;
import observer.*;
import java.util.*;

public class SmartHomeSystem {
    public static void main(String[] args) {
        SmartHub hub = new SmartHub();
        Scanner sc = new Scanner(System.in);

        hub.addObserver(new UserApp("MobileApp"));
        hub.addObserver(new UserApp("WebDashboard"));

        boolean running = true;
        while (running) {
            System.out.println("\n--- SMART HOME MENU ---");
            System.out.println("1. Add Device");
            System.out.println("2. Remove Device");
            System.out.println("3. Turn On Device");
            System.out.println("4. Turn Off Device");
            System.out.println("5. Set Schedule");
            System.out.println("6. Add Trigger");
            System.out.println("7. View Status");
            System.out.println("8. View Schedules");
            System.out.println("9. View Triggers");
            System.out.println("10. Authorize/Deauthorize Device");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter device type (0=Light, 1=Thermostat, 2=Door): ");
                    int typeCode = sc.nextInt();
                    System.out.print("Enter device ID: ");
                    int id = sc.nextInt();
                    System.out.print("Protect this device? (1=Yes, 0=No): ");
                    int protect = sc.nextInt();
                    try {
                        SmartDevice device = DeviceFactory.createDevice(typeCode, id);
                        if (protect == 1)
                            hub.addDevice(new DeviceProxy(device, false));
                        else
                            hub.addDevice(device);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter device ID to remove: ");
                    hub.removeDevice(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter device ID to turn ON: ");
                    hub.turnOn(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter device ID to turn OFF: ");
                    hub.turnOff(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter device ID: ");
                    int schId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter time (HH:MM): ");
                    String time = sc.nextLine();
                    System.out.print("Enter command (0=Turn OFF, 1=Turn ON): ");
                    int cmd = sc.nextInt();
                    hub.setSchedule(schId, time, cmd == 0 ? "Turn Off" : "Turn On");
                    break;

                case 6:
                    System.out.print("Enter condition (e.g., temperature > 75): ");
                    String condition = sc.nextLine();

                    if (!condition.matches("temperature\\s*(>|<|>=|<=)\\s*\\d+")) {
                        System.out.println("Invalid condition! Use format: 'temperature > 75' or 'temperature <= 60'.");
                        break;
                    }

                    System.out.print("Enter action (format: <deviceId> <0|1>, where 0=Off, 1=On): ");
                    String action = sc.nextLine();

                    // Example valid action: "1 0" → Device 1 OFF, "2 1" → Device 2 ON
                    String[] parts = action.trim().split("\\s+");
                    if (parts.length != 2) {
                        System.out.println("Invalid action format! Use: '<deviceId> <0|1>'.");
                        break;
                    }

                    try {
                        int devId = Integer.parseInt(parts[0]);
                        int actionCmd  = Integer.parseInt(parts[1]);

                        if (actionCmd  != 0 && actionCmd  != 1) {
                            System.out.println("Invalid command! Use 0 for Off, 1 for On.");
                            break;
                        }

                        // If all valid, add trigger
                        String actionFormatted = "Device " + devId + (actionCmd  == 0 ? " Turn Off" : " Turn On");
                        hub.addTrigger(condition, actionFormatted);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid action input! DeviceId and Command must be numbers.");
                    }
                    break;

                case 7:
                    hub.viewStatus();
                    break;
                case 8:
                    hub.viewSchedules();
                    break;
                case 9:
                    hub.viewTriggers();
                    break;

                case 10:
                    System.out.print("Enter device ID: ");
                    int authId = sc.nextInt();
                    System.out.print("Authorize? (1=Yes, 0=No): ");
                    boolean auth = sc.nextInt() == 1;
                    hub.setAuthorization(authId, auth);
                    break;

                case 11:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}

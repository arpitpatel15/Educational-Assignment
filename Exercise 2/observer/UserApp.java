package observer;

public class UserApp implements Observer {
    private String name;
    public UserApp(String name) { this.name = name; }

    @Override
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}

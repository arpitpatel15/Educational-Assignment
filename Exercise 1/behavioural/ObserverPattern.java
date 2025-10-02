import java.util.*;

interface Observer {
    void update(String message);
}


class User implements Observer {
    private String name;
    public User(String name) { this.name = name; }

    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

class Channel {
    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer o) { observers.add(o); }
    public void unsubscribe(Observer o) { observers.remove(o); }

    public void notifyObservers(String msg) {
        for (Observer o : observers) {
            o.update(msg);
        }
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Channel channel = new Channel();

        Observer user1 = new User("Alice");
        Observer user2 = new User("Bob");

        channel.subscribe(user1);
        channel.subscribe(user2);

        channel.notifyObservers("New Video Uploaded!");
    }
}

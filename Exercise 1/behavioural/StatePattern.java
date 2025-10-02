
interface State {
    void doAction();
}

class StartState implements State {
    public void doAction() {
        System.out.println("Player is in START state.");
    }
}

class StopState implements State {
    public void doAction() {
        System.out.println("Player is in STOP state.");
    }
}

class Player {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void applyState() {
        state.doAction();
    }
}

public class StatePattern {
    public static void main(String[] args) {
        Player player = new Player();

        State start = new StartState();
        State stop = new StopState();

        player.setState(start);
        player.applyState();

        player.setState(stop);
        player.applyState();
    }
}

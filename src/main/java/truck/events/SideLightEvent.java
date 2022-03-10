package truck.events;

public class SideLightEvent {
    private final int id;
    private final boolean state;

    public SideLightEvent(int id, boolean state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return this.id;
    }

    public boolean getState() {
        return state;
    }
}


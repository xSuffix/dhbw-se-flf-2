package truck.events;

public class BlueLightEvent {
    private final int id;
    private final boolean state;

    public BlueLightEvent(int id, boolean state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return this.id;
    }

    public boolean getState() {
        return this.state;
    }
}

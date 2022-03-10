package truck.events;

public class RoofLightEvent {
    private final int id;
    private final boolean state;

    public RoofLightEvent(int id, boolean state) {
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

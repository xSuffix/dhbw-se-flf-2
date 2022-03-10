package truck.events;

public class FrontLightEvent {

    private final int id;
    private final boolean state;

    public FrontLightEvent(int id, boolean state) {
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

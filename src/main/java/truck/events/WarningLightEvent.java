package truck.events;

public class WarningLightEvent {

    private final int id;
    private final boolean state;

    public WarningLightEvent(int id, boolean state){
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

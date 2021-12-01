package controls;

public class RoofLauncherKnob extends TurningKnob {

    private RoofLauncherOutput state;

    public RoofLauncherKnob() {
        this.state = RoofLauncherOutput.A;
    }

    public void turnLeft() {
        this.state = this.state.getPrevious();
    }

    public void turnRight() {
        this.state = this.state.getNext();
    }

    public int getValue() {
        return this.state.getValue();
    }

}

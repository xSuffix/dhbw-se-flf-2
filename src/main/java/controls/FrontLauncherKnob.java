package controls;

public class FrontLauncherKnob {

    private FrontLauncherOutput state;

    public FrontLauncherKnob() {
        this.state = FrontLauncherOutput.A;
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

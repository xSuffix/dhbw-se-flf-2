package drive;

public class ElectricMotor {

    private boolean started;

    public ElectricMotor() {
        this.started = false;
    }

    public int rotate(int vel) {
        return vel;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

}

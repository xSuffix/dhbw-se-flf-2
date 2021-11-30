package drive;

public class SteeringAxle extends Axle {

    private int rotation;

    public SteeringAxle() {
        super();
        this.rotation = 0;
    }

    // Directly mirror rotation of steering wheel!
    public void rotate(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }
}

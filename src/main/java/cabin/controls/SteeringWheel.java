package cabin.controls;

import truck.ICentralUnit;

public class SteeringWheel implements ISteeringWheel{

    private final ICentralUnit centralUnit;
    private int rotation;

    public SteeringWheel(ICentralUnit centralUnit) {
        this.centralUnit = centralUnit;
        rotation = 0;
    }

    public void rotate(int rotation) {
        this.rotation += rotation;
        if (this.rotation < -90) {
            this.rotation = -90;
        } else if (this.rotation > 90) {
            this.rotation = 90;
        }
        centralUnit.turnSteeringWheel(this.rotation);
    }

    public int getRotation() {
        return this.rotation;
    }

}

package cabin;

import truck.CentralUnit;

public class SteeringWheel {
    private int rotation;
    private CentralUnit centralUnit;

    public SteeringWheel(CentralUnit centralUnit){
        this.centralUnit = centralUnit;
        rotation = 0;
    }

    public void rotate(int rotation){
        this.rotation += rotation;
        if(this.rotation < -90){
            this.rotation = -90;
        } else if(this.rotation > 90){
            this.rotation = 90;
        }
        centralUnit.turnSteeringWheel(this.rotation);
    }

    public int getRotation(){
        return this.rotation;
    }

}

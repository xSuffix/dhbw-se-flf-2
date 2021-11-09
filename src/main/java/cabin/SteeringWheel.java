package cabin;

public class SteeringWheel {
    private int rotation;

    public SteeringWheel(){
        rotation = 0;
    }

    public void rotate(int rotation){
        this.rotation += rotation;
        if(this.rotation < -90){
            this.rotation = -90;
        } else if(this.rotation > 90){
            this.rotation = 90;
        }
    }

    public int getRotation(){
        return this.rotation;
    }

}

package drive;

public class ElectricMotor {
    private boolean isStarted;

    public ElectricMotor(){
        this.isStarted = false;
    }

    public int rotate(int vel){
        return vel;
    }

    public void start(){
        this.isStarted = true;
    }

    public void stop(){
        this.isStarted = false;
    }

    public boolean isStarted(){
        return this.isStarted;
    }
}

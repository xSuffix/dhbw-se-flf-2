package drive;

public class Drive {

    private final ElectricMotor[] electricMotors;
    private final BatteryManagement batteryManagement = BatteryManagement.INSTANCE;
    private final BatteryBox batteryBox;
    private final SteeringAxle[] frontAxles;
    private final Axle[] backAxles;
    private int currentVelocity;

    public Drive(){
        this.frontAxles = new SteeringAxle[2];
        for(int i = 0;i < frontAxles.length;i++){
            frontAxles[i] = new SteeringAxle();
        }
        this.backAxles = new Axle[2];
        for(int i = 0;i < backAxles.length;i++){
            backAxles[i] = new Axle();
        }
        this.electricMotors = new ElectricMotor[2];
        for(int i = 0;i<electricMotors.length;i++){
            electricMotors[i] = new ElectricMotor();
        }
        this.batteryBox = new BatteryBox();
        this.currentVelocity = 0;
    }

    public void stopMotors(){
        for(ElectricMotor motor : electricMotors){
            motor.stop();
        }
    }

    public void startMotors(){
        for(ElectricMotor motor : electricMotors){
            motor.start();
        }
    }

    public void drive(int vel){
        if(batteryManagement.getCharge(batteryBox) >= vel*4){
            for(ElectricMotor motor : electricMotors){
                motor.rotate(vel);
                currentVelocity = vel;
            }
            batteryManagement.takeEnergy(batteryBox,vel*4);
        }
    }

    public int getCurrentVelocity() {
        return currentVelocity;
    }

    public void rotateAxles(int rotation){
        for(SteeringAxle a : frontAxles){
            a.rotate(rotation);
        }
    }

    public int getAxleRotation(){
        int rotation = frontAxles[0].getRotation();
        for(SteeringAxle a : frontAxles){
            if(a.getRotation() != rotation){
                throw new RuntimeException("Your axles are broken!!!");
            }
        }
        return rotation;
    }

    public float getBatteryPercentage(){
        return batteryManagement.getChargePercentage(batteryBox);
    }
}

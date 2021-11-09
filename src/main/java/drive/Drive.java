package drive;

public class Drive {

    private final ElectricMotor[] electricMotors;
    private final BatteryManagement batteryManagement = BatteryManagement.INSTANCE;
    private final BatteryBox batteryBox;

    public Drive(){
        this.electricMotors = new ElectricMotor[2];
        for(int i = 0;i<electricMotors.length;i++){
            electricMotors[i] = new ElectricMotor();
        }
        this.batteryBox = new BatteryBox();
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
            }
            batteryManagement.takeEnergy(batteryBox,vel*4);
        }
    }
}

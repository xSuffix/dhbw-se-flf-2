package drive;

public class Drive {

    private final ElectricMotor[] electricMotors;
    private final BatteryManagement batteryManagement = BatteryManagement.INSTANCE;
    private final BatteryBox batteryBox;
    private final SteeringAxle[] frontAxles;
    private final Axle[] backAxles;
    private int currentVelocity;

    public Drive(ElectricMotor[] motors, BatteryBox box, SteeringAxle[] frontAxles, Axle[] backAxles) {
        this.electricMotors = motors;
        this.batteryBox = box;
        this.frontAxles = frontAxles;
        this.backAxles = backAxles;
        this.currentVelocity = 0;
    }

    public void stopMotors() {
        for (ElectricMotor motor : electricMotors) {
            motor.stop();
        }
    }

    public void startMotors() {
        for (ElectricMotor motor : electricMotors) {
            motor.start();
        }
    }

    public boolean motorsOn() {
        for (ElectricMotor motor : electricMotors) {
            if (!motor.isStarted()) return false;
        }
        return true;
    }

    public void charge(int amount) {
        batteryManagement.charge(batteryBox, amount);
    }

    public void drive(int vel) {
        if (batteryManagement.getCharge(batteryBox) >= vel * 4) {
            for (ElectricMotor motor : electricMotors) {
                motor.rotate(vel);
                currentVelocity = vel;
            }
            int receivedEnergy = batteryManagement.takeEnergy(batteryBox, vel * 4);
            System.out.println(receivedEnergy);
            if (receivedEnergy < vel * 4) throw new RuntimeException("Battery empty, shutting down :)");
        }
    }

    public int getCurrentVelocity() {
        return currentVelocity;
    }

    public void rotateAxles(int rotation) {
        for (SteeringAxle a : frontAxles) {
            a.rotate(rotation);
        }
    }

    public int getAxleRotation() {
        int rotation = frontAxles[0].getRotation();
        for (SteeringAxle a : frontAxles) {
            if (a.getRotation() != rotation) {
                throw new RuntimeException("Your axles are broken!!!");
            }
        }
        return rotation;
    }

    public int getBatteryCharge() {
        return batteryManagement.getCharge(batteryBox);
    }

    public float getBatteryPercentage() {
        return batteryManagement.getChargePercentage(batteryBox);
    }

    public ElectricMotor[] getElectricMotors() {
        return this.electricMotors;
    }

    public BatteryManagement getBatteryManagement() {
        return batteryManagement;
    }

    public BatteryBox getBatteryBox() {
        return batteryBox;
    }

    public SteeringAxle[] getFrontAxles() {
        return frontAxles;
    }

    public Axle[] getBackAxles() {
        return backAxles;
    }
}

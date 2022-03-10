package drive;

import com.google.common.eventbus.Subscribe;
import drive.battery.BatteryBox;
import drive.battery.BatteryManagement;
import drive.battery.IBatteryBox;
import drive.battery.charger.Receiver;
import truck.events.EngineEvent;
import truck.events.Subscriber;

public class Drive extends Subscriber implements IDrive {

    private final BatteryManagement batteryManagement = BatteryManagement.INSTANCE;
    private final ElectricMotor[] electricMotors;
    private final SteeringAxle[] frontAxles;
    private final Axle[] backAxles;
    private int currentVelocity;

    public Drive(ElectricMotor[] motors, BatteryBox batteryBox, Receiver energyReceiver, SteeringAxle[] frontAxles, Axle[] backAxles) {
        this.electricMotors = motors;
        this.frontAxles = frontAxles;
        this.backAxles = backAxles;
        this.currentVelocity = 0;
        batteryManagement.setBatteryBox(batteryBox);
        batteryManagement.setReceiver(energyReceiver);
    }

    @Subscribe
    public void receive(EngineEvent event) {
        setMotorStarted(event.getState());
    }

    public void setMotorStarted(boolean start) {
        for (ElectricMotor motor : electricMotors) motor.setStarted(start);
    }

    public boolean motorsOn() {
        for (ElectricMotor motor : electricMotors) {
            if (!motor.isStarted()) return false;
        }
        return true;
    }

    public void charge(int amount) {
        batteryManagement.charge(amount);
    }

    public void drive(int vel) {
        if (batteryManagement.getCharge() >= vel * 4) {
            for (ElectricMotor motor : electricMotors) {
                motor.rotate(vel);
                currentVelocity = vel;
            }
            int receivedEnergy = batteryManagement.takeEnergy(vel * 4);
            System.out.printf("[Drive] driving %d km/h%n", vel);
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
        return batteryManagement.getCharge();
    }

    public float getBatteryPercentage() {
        return batteryManagement.getChargePercentage();
    }

    public ElectricMotor[] getElectricMotors() {
        return this.electricMotors;
    }

    public BatteryManagement getBatteryManagement() {
        return batteryManagement;
    }

    public IBatteryBox getBatteryBox() {
        return batteryManagement.getBatteryBox();
    }

    public SteeringAxle[] getFrontAxles() {
        return frontAxles;
    }

    public Axle[] getBackAxles() {
        return backAxles;
    }

}

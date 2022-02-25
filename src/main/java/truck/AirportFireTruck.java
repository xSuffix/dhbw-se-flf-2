package truck;

import cabin.Cabin;
import drive.*;
import drive.battery.BatteryBox;
import lights.*;
import truck.water.*;

public class AirportFireTruck implements IAirportFireTruck {
    private final Light[] headLightsFrontLeft;
    private final Light[] headLightsFrontRight;
    private final Light[] headLightsRoof;
    private final Light[] sideLightsLeft;
    private final Light[] sideLightsRight;
    private final Light[] turnSignalLightsLeft;
    private final Light[] turnSignalLightsRight;
    private final Light brakeLightLeft;
    private final Light brakeLightRight;
    private final Light[] blueLights;
    private final Light[] warningLights;
    private final IDrive drive;
    private final Cabin cabin;
    private final ICentralUnit centralUnit;
    private final ITank waterTank;
    private final ITank foamPowderTank;
    private final IMixingUnit mixingUnit;
    private final FrontLauncher frontLauncher;
    private final RoofLauncher roofLauncher;
    private final FloorSprayingNozzle[] floorSprayingNozzles;

    private AirportFireTruck(Builder builder, boolean smartJoySticks) {
        this.headLightsFrontLeft = builder.headLightsFrontLeft;
        this.headLightsFrontRight = builder.headLightsFrontRight;
        this.headLightsRoof = builder.headLightsRoof;
        this.sideLightsLeft = builder.sideLightsLeft;
        this.sideLightsRight = builder.sideLightsRight;
        this.turnSignalLightsLeft = builder.turnSignalLightsLeft;
        this.turnSignalLightsRight = builder.turnSignalLightsRight;
        this.brakeLightLeft = builder.brakeLightLeft;
        this.brakeLightRight = builder.brakeLightRight;
        this.blueLights = builder.blueLights;
        this.warningLights = builder.warningLights;
        this.drive = builder.drive;
        this.centralUnit = new CentralUnit(this);
        this.cabin = new Cabin(this.centralUnit, smartJoySticks);
        this.waterTank = builder.waterTank;
        this.foamPowderTank = builder.foamPowderTank;
        this.mixingUnit = builder.mixingUnit;
        this.frontLauncher = builder.frontLauncher;
        this.roofLauncher = builder.roofLauncher;
        this.floorSprayingNozzles = builder.floorSprayingNozzles;
    }

    public void chargeTruck(int amount) {
        this.getDrive().charge(amount);
        this.getCabin().getBatteryDisplay().setValue(String.valueOf(this.getDrive().getBatteryPercentage()));
    }

    public void useFloorNozzles(int amount) {
        for (FloorSprayingNozzle nozzle : floorSprayingNozzles) {
            nozzle.sprayWater(amount);
        }
    }

    public Light[] getHeadLightsFrontLeft() {
        return headLightsFrontLeft;
    }

    public Light[] getHeadLightsFrontRight() {
        return headLightsFrontRight;
    }

    public Light[] getHeadLightsRoof() {
        return headLightsRoof;
    }

    public Light[] getSideLightsLeft() {
        return sideLightsLeft;
    }

    public Light[] getSideLightsRight() {
        return sideLightsRight;
    }

    public Light[] getTurnSignalLightsLeft() {
        return turnSignalLightsLeft;
    }

    public Light[] getTurnSignalLightsRight() {
        return turnSignalLightsRight;
    }

    public Light getBrakeLightLeft() {
        return brakeLightLeft;
    }

    public Light getBrakeLightRight() {
        return brakeLightRight;
    }

    public Light[] getBlueLights() {
        return blueLights;
    }

    public Light[] getWarningLights() {
        return warningLights;
    }

    public IDrive getDrive() {
        return drive;
    }

    public ICentralUnit getCentralUnit() {
        return centralUnit;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public ITank getWaterTank() {
        return waterTank;
    }

    public ITank getFoamPowderTank() {
        return foamPowderTank;
    }

    public IMixingUnit getMixingUnit() {
        return mixingUnit;
    }

    public FrontLauncher getFrontLauncher() {
        return frontLauncher;
    }

    public RoofLauncher getRoofLauncher() {
        return roofLauncher;
    }

    public FloorSprayingNozzle[] getFloorSprayingNozzles() {
        return floorSprayingNozzles;
    }


    public static class Builder {
        private final HeadLight[] headLightsFrontLeft;
        private final HeadLight[] headLightsFrontRight;
        private final HeadLight[] headLightsRoof;
        private final HeadLight[] sideLightsLeft;
        private final HeadLight[] sideLightsRight;
        private final TurnSignalLight[] turnSignalLightsLeft;
        private final TurnSignalLight[] turnSignalLightsRight;
        private final BrakeLight brakeLightLeft;
        private final BrakeLight brakeLightRight;
        private final BlueLight[] blueLights;
        private final WarningLight[] warningLights;
        private final Drive drive;
        private final Tank waterTank;
        private final Tank foamPowderTank;
        private final MixingUnit mixingUnit;
        private final FrontLauncher frontLauncher;
        private final RoofLauncher roofLauncher;
        private final FloorSprayingNozzle[] floorSprayingNozzles;
        private final boolean smartJoySticks;

        public Builder(boolean smartJoySticks) {
            this.smartJoySticks = smartJoySticks;
            this.headLightsFrontLeft = new HeadLight[3];
            this.headLightsFrontRight = new HeadLight[3];
            for (int i = 0; i < headLightsFrontLeft.length; i++) {
                this.headLightsFrontLeft[i] = new HeadLight();
                this.headLightsFrontRight[i] = new HeadLight();
            }
            this.headLightsRoof = new HeadLight[4];
            for (int i = 0; i < headLightsRoof.length; i++) {
                this.headLightsRoof[i] = new HeadLight();
            }
            this.sideLightsLeft = new HeadLight[5];
            this.sideLightsRight = new HeadLight[5];
            for (int i = 0; i < sideLightsLeft.length; i++) {
                this.sideLightsLeft[i] = new HeadLight();
                this.sideLightsRight[i] = new HeadLight();
            }

            this.turnSignalLightsLeft = new TurnSignalLight[2];
            this.turnSignalLightsRight = new TurnSignalLight[2];
            for (int i = 0; i < turnSignalLightsLeft.length; i++) {
                this.turnSignalLightsLeft[i] = new TurnSignalLight();
                this.turnSignalLightsRight[i] = new TurnSignalLight();
            }

            this.brakeLightLeft = new BrakeLight();
            this.brakeLightRight = new BrakeLight();

            this.blueLights = new BlueLight[10];
            // front small
            for (int i = 0; i < 2; i++) {
                this.blueLights[i] = new BlueLight(1);
            }
            // front roof big
            for (int i = 2; i < 6; i++) {
                this.blueLights[i] = new BlueLight(4);
            }
            // back roof medium
            for (int i = 6; i < 10; i++) {
                this.blueLights[i] = new BlueLight(2);
            }

            this.warningLights = new WarningLight[2];
            for (int i = 0; i < warningLights.length; i++) {
                this.warningLights[i] = new WarningLight(1);
            }

            ElectricMotor[] electricMotors = new ElectricMotor[2];
            for (int i = 0; i < electricMotors.length; i++) {
                electricMotors[i] = new ElectricMotor();
            }
            BatteryBox batteryBox = new BatteryBox();
            SteeringAxle[] frontAxles = new SteeringAxle[2];
            for (int i = 0; i < frontAxles.length; i++) {
                frontAxles[i] = new SteeringAxle();
            }
            Axle[] backAxles = new Axle[2];
            for (int i = 0; i < backAxles.length; i++) {
                backAxles[i] = new Axle();
            }
            this.drive = new Drive(electricMotors, batteryBox, frontAxles, backAxles);

            this.waterTank = new Tank(ExtinguishingType.WATER, 75, 45, 30);
            this.foamPowderTank = new Tank(ExtinguishingType.FOAM_POWDER, 75, 45, 10);
            this.mixingUnit = new MixingUnit(this.waterTank, this.foamPowderTank);
            this.frontLauncher = new FrontLauncher(this.mixingUnit);
            this.roofLauncher = new RoofLauncher(this.mixingUnit);
            this.floorSprayingNozzles = new FloorSprayingNozzle[7];
            for (int i = 0; i < floorSprayingNozzles.length; i++) {
                this.floorSprayingNozzles[i] = new FloorSprayingNozzle(this.waterTank);
            }
        }

        public AirportFireTruck build() {
            return new AirportFireTruck(this,smartJoySticks);
        }

    }

}

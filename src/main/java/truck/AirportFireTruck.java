package truck;

import cabin.Cabin;
import drive.*;
import enums.ExtinguishingType;
import lights.*;

public class AirportFireTruck implements IAirportFireTruck{
    private final TurnSignalLight[] turnSignalLightLeft;
    private final TurnSignalLight[] turnSignalLightRight;
    private final BlueLight[] blueLights;
    private final WarningLight[] warningLights;
    private final BrakeLight brakeLightLeft;
    private final BrakeLight brakeLightRight;
    private final HeadLight[] headLightsFrontLeft;
    private final HeadLight[] headLightsFrontRight;
    private final HeadLight[] headLightsRoof;
    private final Drive drive;
    private final Cabin cabin;
    private final CentralUnit centralUnit;
    private final Tank waterTank;
    private final Tank foampowderTank;
    private final MixingUnit mixingUnit;
    private final FrontLauncher frontLauncher;
    private final RoofLauncher roofLauncher;
    private final FloorSprayingNoozle[] floorSprayingNoozles;

    private AirportFireTruck(Builder builder){
        this.centralUnit = new CentralUnit(this);
        this.cabin = new Cabin(this.centralUnit);
        this.drive = builder.drive;
        this.headLightsFrontLeft = builder.headLightsFrontLeft;
        this.headLightsFrontRight = builder.headLightsFrontRight;
        this.headLightsRoof = builder.headLightsRoof;
        this.brakeLightLeft = builder.brakeLightLeft;
        this.brakeLightRight = builder.brakeLightRight;
        this.blueLights = builder.blueLights;
        this.warningLights = builder.warningLights;
        this.turnSignalLightLeft = builder.turnSignalLightLeft;
        this.turnSignalLightRight = builder.turnSignalLightRight;
        this.waterTank = builder.waterTank;
        this.foampowderTank = builder.foampowderTank;
        this.mixingUnit = builder.mixingUnit;
        this.frontLauncher = builder.frontLauncher;
        this.roofLauncher = builder.roofLauncher;
        this.floorSprayingNoozles = builder.floorSprayingNoozles;
    }

    public void chargeTruck(int amount){
        this.getDrive().charge(amount);
        this.getCabin().getBatteryDisplay().writeValue(String.valueOf(this.getDrive().getBatteryPercentage()));
    }

    public BlueLight[] getBlueLights() {
        return blueLights;
    }

    public WarningLight[] getWarningLights() {
        return warningLights;
    }

    public TurnSignalLight[] getTurnSignalLightLeft() {
        return turnSignalLightLeft;
    }

    public TurnSignalLight[] getTurnSignalLightRight() {
        return turnSignalLightRight;
    }

    public BrakeLight getBrakeLightLeft() {
        return brakeLightLeft;
    }

    public BrakeLight getBrakeLightRight() {
        return brakeLightRight;
    }

    public HeadLight[] getHeadLightsFrontLeft() {
        return headLightsFrontLeft;
    }

    public HeadLight[] getHeadLightsFrontRight() {
        return headLightsFrontRight;
    }

    public HeadLight[] getHeadLightsRoof() {
        return headLightsRoof;
    }

    public Drive getDrive() {
        return drive;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }

    public Tank getWaterTank() {
        return waterTank;
    }

    public Tank getFoampowderTank() {
        return foampowderTank;
    }

    public MixingUnit getMixingUnit() {
        return mixingUnit;
    }

    public FrontLauncher getFrontLauncher() {
        return frontLauncher;
    }

    public RoofLauncher getRoofLauncher() {
        return roofLauncher;
    }

    public FloorSprayingNoozle[] getFloorSprayingNoozles() {
        return floorSprayingNoozles;
    }

    public static class Builder {

        private final TurnSignalLight[] turnSignalLightLeft;
        private final TurnSignalLight[] turnSignalLightRight;
        private final BlueLight[] blueLights;
        private final WarningLight[] warningLights;
        private final BrakeLight brakeLightLeft;
        private final BrakeLight brakeLightRight;
        private final HeadLight[] headLightsFrontLeft;
        private final HeadLight[] headLightsFrontRight;
        private final HeadLight[] headLightsRoof;
        private final Drive drive;
        private final Tank waterTank;
        private final Tank foampowderTank;
        private final MixingUnit mixingUnit;
        private final FrontLauncher frontLauncher;
        private final RoofLauncher roofLauncher;
        private final FloorSprayingNoozle[] floorSprayingNoozles;
        private final ElectricMotor[] electricMotors;
        private final BatteryBox batteryBox;
        private final SteeringAxle[] frontAxles;
        private final Axle[] backAxles;

        public Builder(){
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
            this.turnSignalLightLeft = new TurnSignalLight[2];
            this.turnSignalLightRight = new TurnSignalLight[2];
            for(int i = 0;i<turnSignalLightLeft.length;i++){
                this.turnSignalLightLeft[i] = new TurnSignalLight();
                this.turnSignalLightRight[i] = new TurnSignalLight();
            }
            this.blueLights = new BlueLight[10];
            // front small
            for(int i = 0;i<2;i++){
                this.blueLights[i] = new BlueLight(1);
            }
            // front roof big
            for(int i = 2;i<6;i++){
                this.blueLights[i] = new BlueLight(4);
            }
            // back roof medium
            for(int i = 6;i<10;i++){
                this.blueLights[i] = new BlueLight(2);
            }
            this.warningLights = new WarningLight[2];
            for(int i = 0;i<warningLights.length;i++){
                this.warningLights[i] = new WarningLight(1);
            }
            this.brakeLightLeft = new BrakeLight();
            this.brakeLightRight = new BrakeLight();
            this.headLightsFrontLeft = new HeadLight[3];
            this.headLightsFrontRight = new HeadLight[3];
            for(int i = 0;i<headLightsFrontLeft.length;i++){
                this.headLightsFrontLeft[i] = new HeadLight();
                this.headLightsFrontRight[i] = new HeadLight();
            }
            this.headLightsRoof = new HeadLight[4];
            for(int i = 0;i<headLightsRoof.length;i++){
                this.headLightsRoof[i] = new HeadLight();
            }
            this.drive = new Drive(this.electricMotors,this.batteryBox,this.frontAxles,this.backAxles);
            this.waterTank = new Tank(ExtinguishingType.WATER,50,25,10);
            this.foampowderTank = new Tank(ExtinguishingType.FOAMPOWDER,25,10,10);
            this.mixingUnit = new MixingUnit(this.waterTank, this.foampowderTank);
            this.frontLauncher = new FrontLauncher(this.mixingUnit);
            this.roofLauncher = new RoofLauncher(this.mixingUnit);
            this.floorSprayingNoozles = new FloorSprayingNoozle[7];
            for (int i = 0; i < floorSprayingNoozles.length;i++){
                this.floorSprayingNoozles[i] = new FloorSprayingNoozle(this.waterTank);
            }

        }

        public AirportFireTruck build(){
            return new AirportFireTruck(this);
        }
    }

}

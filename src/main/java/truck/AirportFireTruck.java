package truck;

import cabin.Cabin;
import drive.Drive;
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

        public Builder(){
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
            this.drive = new Drive();
        }

        public AirportFireTruck build(){
            return new AirportFireTruck(this);
        }
    }

}

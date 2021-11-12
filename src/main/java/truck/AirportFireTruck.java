package truck;

import cabin.Cabin;
import drive.Drive;
import lights.*;

public class AirportFireTruck implements IAirportFireTruck{
    private TurnSignalLight[] turnSignalLightLeft;
    private TurnSignalLight[] turnSignalLightRight;
    private BlueLight[] blueLights;
    private WarningLight[] warningLights;
    private BrakeLight brakeLightLeft;
    private BrakeLight brakeLightRight;
    private HeadLight[] headLightsFrontLeft;
    private HeadLight[] headLightsFrontRight;
    private HeadLight[] headLightsRoof;
    private Drive drive;
    private Cabin cabin;
    private CentralUnit centralUnit;

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



}

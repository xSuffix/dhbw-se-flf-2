package truck;

import cabin.Cabin;
import drive.Drive;
import lights.*;

public interface IAirportFireTruck {

    void chargeTruck(int amount);

    void useFloorNozzles(int amount);

    Light[] getHeadLightsFrontLeft();

    Light[] getHeadLightsFrontRight();

    Light[] getHeadLightsRoof();

    Light[] getSideLightsLeft();

    Light[] getSideLightsRight();

    Light[] getTurnSignalLightsLeft();

    Light[] getTurnSignalLightsRight();

    Light getBrakeLightLeft();

    Light getBrakeLightRight();

    Light[] getBlueLights();

    Light[] getWarningLights();

    Drive getDrive();

    CentralUnit getCentralUnit();

    Cabin getCabin();

    Tank getWaterTank();

    Tank getFoamPowderTank();

    MixingUnit getMixingUnit();

    FrontLauncher getFrontLauncher();

    RoofLauncher getRoofLauncher();

    FloorSprayingNozzle[] getFloorSprayingNozzles();

}

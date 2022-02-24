package truck;

import cabin.Cabin;
import drive.IDrive;
import lights.*;
import truck.water.*;

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

    IDrive getDrive();

    ICentralUnit getCentralUnit();

    Cabin getCabin();

    ITank getWaterTank();

    ITank getFoamPowderTank();

    IMixingUnit getMixingUnit();

    FrontLauncher getFrontLauncher();

    RoofLauncher getRoofLauncher();

    FloorSprayingNozzle[] getFloorSprayingNozzles();

}

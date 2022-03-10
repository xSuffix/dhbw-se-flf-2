package truck;

import cabin.Cabin;
import drive.IDrive;
import lights.Light;
import truck.water.FloorSprayingNozzle;
import truck.water.FrontLauncher;
import truck.water.ITank;
import truck.water.RoofLauncher;

public interface IAirportFireTruck {

    void chargeTruck(int amount);

    // void useFloorNozzles(int amount);

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

    Object getMixingUnit();

    FrontLauncher getFrontLauncher();

    RoofLauncher getRoofLauncher();

    FloorSprayingNozzle[] getFloorSprayingNozzles();

}

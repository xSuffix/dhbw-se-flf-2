package truck;

import cabin.Cabin;
import drive.Drive;
import lights.*;

public interface IAirportFireTruck {

    void chargeTruck(int amount);

    HeadLight[] getHeadLightsFrontLeft();

    HeadLight[] getHeadLightsFrontRight();

    HeadLight[] getHeadLightsRoof();

    TurnSignalLight[] getTurnSignalLightLeft();

    TurnSignalLight[] getTurnSignalLightRight();

    BrakeLight getBrakeLightLeft();

    BrakeLight getBrakeLightRight();

    BlueLight[] getBlueLights();

    WarningLight[] getWarningLights();

    Drive getDrive();

    CentralUnit getCentralUnit();

    Cabin getCabin();

    Tank getWaterTank();

    Tank getFoamPowderTank();

    MixingUnit getMixingUnit();

    FrontLauncher getFrontLauncher();

    RoofLauncher getRoofLauncher();

    FloorSprayingNoozle[] getFloorSprayingNoozles();

    void useFloorNoozles(int amount);
}

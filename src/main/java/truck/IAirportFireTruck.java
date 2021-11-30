package truck;

import cabin.Cabin;
import drive.Drive;
import lights.BlueLight;
import lights.HeadLight;
import lights.TurnSignalLight;
import lights.WarningLight;

public interface IAirportFireTruck {

    void chargeTruck(int amount);

    Drive getDrive();

    Cabin getCabin();

    BlueLight[] getBlueLights();

    WarningLight[] getWarningLights();

    HeadLight[] getHeadLightsRoof();

    HeadLight[] getHeadLightsFrontLeft();

    HeadLight[] getHeadLightsFrontRight();

    TurnSignalLight[] getTurnSignalLightLeft();

    TurnSignalLight[] getTurnSignalLightRight();

    Tank getWaterTank();

    Tank getFoampowderTank();

    MixingUnit getMixingUnit();

    FrontLauncher getFrontLauncher();

    RoofLauncher getRoofLauncher();

    FloorSprayingNoozle[] getFloorSprayingNoozles();

    void useFloorNoozles(int amount);
}

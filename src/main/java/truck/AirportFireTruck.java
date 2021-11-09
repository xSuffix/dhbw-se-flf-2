package truck;

import cabin.Cabin;
import cabin.ControlPanel;
import drive.Drive;
import lights.BrakeLight;
import lights.HeadLight;
import lights.TurnSignalLight;

public class AirportFireTruck implements IAirportFireTruck{
    private TurnSignalLight[] turnSignalLightLeft;
    private TurnSignalLight[] turnSignalLightRight;
    private BrakeLight brakeLightLeft;
    private BrakeLight brakeLightRight;
    private HeadLight[] headLightsFrontLeft;
    private HeadLight[] headLightsFrontRight;
    private HeadLight[] headLightsRoof;
    private Drive drive;
    private Cabin cabin;
    private CentralUnit centralUnit;
}

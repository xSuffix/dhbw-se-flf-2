package cabin;

import enums.PedalType;
import truck.CentralUnit;

public class GasPedal extends Pedal{
    public GasPedal(CentralUnit centralUnit){
        super(PedalType.Gas,centralUnit);
    }
}

package cabin;

import enums.PedalType;
import truck.ICentralUnit;

public class GasPedal extends Pedal {

    public GasPedal(ICentralUnit centralUnit) {
        super(PedalType.Gas, centralUnit);
    }

}

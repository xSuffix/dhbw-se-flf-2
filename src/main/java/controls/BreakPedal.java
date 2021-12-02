package controls;

import enums.PedalType;
import truck.ICentralUnit;

public class BreakPedal extends Pedal {

    public BreakPedal(ICentralUnit centralUnit) {
        super(PedalType.BRAKE, centralUnit);
    }

}

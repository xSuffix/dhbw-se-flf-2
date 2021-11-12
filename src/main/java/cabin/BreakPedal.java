package cabin;

import enums.PedalType;
import truck.CentralUnit;

public class BreakPedal extends Pedal{
    public BreakPedal(CentralUnit centralUnit){
        super(PedalType.Brake,centralUnit);
    }
}

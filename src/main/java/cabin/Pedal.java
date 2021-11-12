package cabin;

import enums.PedalType;
import truck.CentralUnit;

public abstract class Pedal {
    private final PedalType type;
    private final CentralUnit centralUnit;

    public Pedal(PedalType type,CentralUnit centralUnit){
        this.type = type;
        this.centralUnit = centralUnit;
    }
    public void pressPedal(){
        centralUnit.pedalPress(type);
    }
}

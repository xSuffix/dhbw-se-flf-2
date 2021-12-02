package controls;

import enums.PedalType;
import truck.ICentralUnit;

public abstract class Pedal {

    private final PedalType type;
    private final ICentralUnit centralUnit;

    public Pedal(PedalType type, ICentralUnit centralUnit) {
        this.type = type;
        this.centralUnit = centralUnit;
    }

    public void pressPedal() {
        centralUnit.pedalPress(type);
    }

}

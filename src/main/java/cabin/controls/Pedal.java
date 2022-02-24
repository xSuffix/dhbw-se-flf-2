package cabin.controls;

import truck.ICentralUnit;

public class Pedal {

    private final ICentralUnit centralUnit;
    private final PedalType type;

    public Pedal(ICentralUnit centralUnit, PedalType type) {
        this.centralUnit = centralUnit;
        this.type = type;
    }

    public void press() {
        centralUnit.pedalPress(type);
    }

}

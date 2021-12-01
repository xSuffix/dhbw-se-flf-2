package truck;

import enums.ExtinguishingType;
import enums.MixingRatio;

import java.util.ArrayList;
import java.util.Collections;

public class MixingUnit {

    private final Tank waterTank;
    private final Tank foamPowderTank;

    public MixingUnit(Tank water, Tank foam) {
        this.waterTank = water;
        this.foamPowderTank = foam;
    }

    public ArrayList<ExtinguishingType> getMixedAgent(int amount, MixingRatio ratio) {
        ExtinguishingType[] water = waterTank.getAgent(amount - ((amount * ratio.getValue()) / 100));
        ExtinguishingType[] foam = foamPowderTank.getAgent((amount * ratio.getValue()) / 100);
        ArrayList<ExtinguishingType> mixed = new ArrayList<>();

        Collections.addAll(mixed, water);
        Collections.addAll(mixed, foam);
        Collections.shuffle(mixed);
        return mixed;
    }

}

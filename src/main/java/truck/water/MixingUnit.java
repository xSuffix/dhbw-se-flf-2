package truck.water;

import java.util.ArrayList;
import java.util.Collections;

public class MixingUnit implements IMixingUnit{

    private final ITank waterTank;
    private final ITank foamPowderTank;

    public MixingUnit(ITank water, ITank foam) {
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

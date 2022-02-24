package truck.water;

import java.util.ArrayList;

public interface IMixingUnit {
    ArrayList<ExtinguishingType> getMixedAgent(int amount, MixingRatio ratio);
}

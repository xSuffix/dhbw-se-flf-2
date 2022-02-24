package truck.water;

import truck.water.ExtinguishingType;
import truck.water.MixingRatio;

import java.util.ArrayList;

public interface IMixingUnit {
    ArrayList<ExtinguishingType> getMixedAgent(int amount, MixingRatio ratio);
}

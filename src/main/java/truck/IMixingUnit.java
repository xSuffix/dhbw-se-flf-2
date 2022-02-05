package truck;

import java.util.ArrayList;
import enums.ExtinguishingType;
import enums.MixingRatio;

public interface IMixingUnit {
    ArrayList<ExtinguishingType> getMixedAgent(int amount, MixingRatio ratio);
}

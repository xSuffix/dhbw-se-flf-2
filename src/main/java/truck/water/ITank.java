package truck.water;

import truck.water.ExtinguishingType;

public interface ITank {
    void fill(int amount, ExtinguishingType type);

    ExtinguishingType[] getAgent(int amount);

    int getTotalCapacity();

    int getCurrentCapacity();

    float getCurrentFillPercentage();

    ExtinguishingType getType();

}

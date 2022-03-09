package drive.battery;

public interface IBatteryBox {

    int getCurrentCharge();

    int getMaxCharge();

    float getCurrentChargePercentage();

    void charge(int amount);

    int takeEnergy(int amount);

    Battery[][] getBatteries();

}

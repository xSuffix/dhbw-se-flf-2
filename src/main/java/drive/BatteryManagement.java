package drive;

public enum BatteryManagement {

    INSTANCE;

    public void charge(IBatteryBox batteryBox,int amount){
        batteryBox.charge(amount);
    }

    public int takeEnergy(IBatteryBox batteryBox,int amount){
        return batteryBox.takeEnergy(amount);
    }

    public int getCharge(IBatteryBox batteryBox){
        return batteryBox.getCurrentCharge();
    }

    public float getChargePercentage(IBatteryBox batteryBox){
        return batteryBox.getCurrentChargePercentage();
    }
}

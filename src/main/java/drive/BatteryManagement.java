package drive;

public enum BatteryManagement {

    INSTANCE;

    public void charge(BatteryBox batteryBox,int amount){
        batteryBox.charge(amount);
    }

    public int takeEnergy(BatteryBox batteryBox,int amount){
        return batteryBox.takeEnergy(amount);
    }

    public int getCharge(BatteryBox batteryBox){
        return batteryBox.getCurrentCharge();
    }

    public float getChargePercentage(BatteryBox batteryBox){
        return batteryBox.getCurrentChargePercentage();
    }
}

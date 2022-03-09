package drive.battery;

import drive.battery.charger.Receiver;

public enum BatteryManagement {
    INSTANCE;

    private IBatteryBox batteryBox;
    private Receiver receiver;

    public IBatteryBox getBatteryBox() {
        return batteryBox;
    }

    public void setBatteryBox(IBatteryBox batteryBox) {
        this.batteryBox = batteryBox;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void charge(int charge) {
        batteryBox.charge(charge);
    }

    public int takeEnergy(int amount) {
        return batteryBox.takeEnergy(amount);
    }

    public int getCharge() {
        return batteryBox.getCurrentCharge();
    }

    public float getChargePercentage() {
        return batteryBox.getCurrentChargePercentage();
    }

}

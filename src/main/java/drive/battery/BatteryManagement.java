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

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void charge(int amount) {
        batteryBox.charge(amount);
    }

    public void discharge() {
        batteryBox.takeEnergy(batteryBox.getCurrentCharge());
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

package drive.battery.charger;

import drive.battery.BatteryManagement;

public class ThreePoleChargingPort extends Receiver implements I3PoleConnector {
    BatteryManagement batteryManagement;
    private I3PoleConnector charger;

    public ThreePoleChargingPort() {
        super(3);
        batteryManagement = BatteryManagement.INSTANCE;
    }

    public I3PoleConnector getCharger() {
        return charger;
    }

    public void plugIn(I3PoleConnector connector) {
        if (charger == null) {
            charger = connector;
            charger.plugIn(this);
        }
    }

    public void plugOut() {
        charger.plugOut();
        charger = null;
    }

    @Override
    public void pushEnergy(int amount) {
        batteryManagement.charge(amount);
    }
}

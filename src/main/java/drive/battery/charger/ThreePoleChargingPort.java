package drive.battery.charger;

import drive.battery.BatteryManagement;

public class ThreePoleChargingPort extends Receiver implements IThreePoleConnector {
    private final BatteryManagement batteryManagement = BatteryManagement.INSTANCE;
    private IThreePoleConnector charger;

    public ThreePoleChargingPort() {
        super(3);
    }

    public IThreePoleConnector getCharger() {
        return charger;
    }

    @Override
    public void plugIn(IThreePoleConnector connector) {
        if (charger == null) {
            charger = connector;
            charger.plugIn(this);
        }
    }

    @Override
    public void plugOut() {
        charger.plugOut();
        charger = null;
    }

    @Override
    public void pushEnergy(int amount) {
        batteryManagement.charge(amount);
    }
}

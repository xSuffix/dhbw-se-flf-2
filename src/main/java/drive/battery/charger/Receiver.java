package drive.battery.charger;

public abstract class Receiver implements IConnector {
    private final ChargingPole[] poles;

    public Receiver(int poleCount) {
        poles = new ChargingPole[poleCount];
        for (int i = 0; i < poles.length; i++) {
            poles[i] = new ChargingPole();
            poles[i].connect(this);
        }
    }

    public ChargingPole[] getChargingPoles() {
        return poles;
    }
}

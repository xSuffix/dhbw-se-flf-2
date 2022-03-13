package drive.battery.charger;

public class EChargingStation implements IOnePoleConnector {
    private final int chargingSpeed;
    private final ChargingPole chargingPole;
    private IOnePoleConnector connector;

    public EChargingStation(int chargingSpeed) {
        this.chargingSpeed = chargingSpeed;
        chargingPole = new ChargingPole();
    }

    public int getChargingSpeed() {
        return chargingSpeed;
    }

    @Override
    public void plugIn(IOnePoleConnector onePoleConnector) {
        if (connector == null) {
            connector = onePoleConnector;
            chargingPole.connect(connector);
        }
    }

    @Override
    public void plugOut() {
        connector = null;
        chargingPole.connect(null);
    }

    @Override
    public void pushEnergy(int amount) {
        chargingPole.pushCharge(Math.min(chargingSpeed, amount));
    }

    public void pushEnergy() {
        chargingPole.pushCharge(chargingSpeed);
    }
}

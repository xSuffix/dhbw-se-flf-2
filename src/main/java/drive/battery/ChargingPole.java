package drive.battery;

public class ChargingPole {
    private IConnector target;

    public void connect(IConnector connector) {
        target = connector;
    }

    public void pushCharge(int amount) {
        if (target != null) target.pushEnergy(amount);
    }
}

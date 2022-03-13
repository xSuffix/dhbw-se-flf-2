package drive.battery.charger;

import java.util.Arrays;

public class OneToThreePoleAdapter implements IOnePoleConnector, IThreePoleConnector {
    private final int[] weights;
    private IOnePoleConnector sender;
    private IThreePoleConnector receiver;

    public OneToThreePoleAdapter(int[] weights) {
        if (weights.length != 3) throw new IllegalArgumentException();
        this.weights = weights;
    }

    public int[] getWeights() {
        return weights;
    }

    @Override
    public void plugIn(IOnePoleConnector connector) {
        if (sender == null) {
            sender = connector;
            sender.plugIn(this);
        }
    }

    @Override
    public void plugIn(IThreePoleConnector connector) {
        if (receiver == null) {
            receiver = connector;
        }
    }

    public void plugOutSender() {
        sender.plugOut();
        sender = null;
    }

    public void plugOutReceiver() {
        receiver.plugOut();
        receiver = null;
    }

    @Override
    public void plugOut() {
        plugOutSender();
        plugOutReceiver();
    }

    @Override
    public void pushEnergy(int amount) {
        if (receiver != null && receiver instanceof Receiver) {
            int weightSum = Arrays.stream(weights).sum();
            ChargingPole[] poles = ((Receiver) receiver).getChargingPoles();
            for (int i = 0; i < poles.length; i++) poles[i].pushCharge(amount * weights[i] / weightSum);
        }
    }
}

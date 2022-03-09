package drive.battery;

public interface IConnector {
    void pushEnergy(int amount);

    void plugOut();
}

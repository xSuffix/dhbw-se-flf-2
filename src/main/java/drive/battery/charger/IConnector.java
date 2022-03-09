package drive.battery.charger;

public interface IConnector {
    void pushEnergy(int amount);

    void plugOut();
}

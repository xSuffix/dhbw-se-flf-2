package drive.battery.charger;

public interface IOnePoleConnector extends IConnector {
    void plugIn(IOnePoleConnector connector);
}

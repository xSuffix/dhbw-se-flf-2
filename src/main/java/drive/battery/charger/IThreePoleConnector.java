package drive.battery.charger;

public interface IThreePoleConnector extends IConnector {
    void plugIn(IThreePoleConnector connector);
}

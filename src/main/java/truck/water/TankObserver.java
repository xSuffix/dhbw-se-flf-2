package truck.water;

public class TankObserver {

    private final ITank tank;
    private ITankObserverListener listener;

    public TankObserver(ITank tank) {
        this.tank = tank;
    }

    public void addListener(ITankObserverListener listener) {
        this.listener = listener;
    }

    public void removeListener() {
        this.listener = null;
    }

    public void checkTankCapacity() {
        float fillPercentage = tank.getCurrentFillPercentage();
        if (fillPercentage <= 1 && fillPercentage > 0.5) {
            listener.noWarning();
        } else if (fillPercentage <= 0.5 && fillPercentage > 0.25) {
            listener.subFifty();
        } else if (fillPercentage <= 0.25 && fillPercentage > 0.1) {
            listener.subTwentyfive();
        } else if (fillPercentage <= 0.1) {
            listener.subTen();
        }
    }

}

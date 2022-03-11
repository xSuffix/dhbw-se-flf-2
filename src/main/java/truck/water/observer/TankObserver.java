package truck.water.observer;

import truck.water.ITank;
import truck.water.observer.ITankObserverListener;

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

        if (Float.compare(50f, fillPercentage) < 1) {
            listener.noWarning();
        } else if (Float.compare(25f, fillPercentage) < 1) {
            listener.subFifty();
        } else if (Float.compare(10f, fillPercentage) < 1) {
            listener.subTwentyfive();
        } else {
            listener.subTen();
        }
    }

}

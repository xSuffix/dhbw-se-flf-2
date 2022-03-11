package lights;

import truck.water.observer.ITankObserverListener;

public class IndicationLight extends LEDLight implements ITankObserverListener {

    public IndicationLight() {
        super(1, LightColor.WHITE);
    }

    @Override
    public void subFifty() {
        color = LightColor.YELLOW;
        if (!isOn())
            toggle();
    }

    @Override
    public void subTwentyfive() {
        color = LightColor.ORANGE;
        if (!isOn())
            toggle();
    }

    @Override
    public void subTen() {
        color = LightColor.RED;
        if (!isOn())
            toggle();
    }

    @Override
    public void noWarning() {
        if (isOn())
            toggle();
    }

}

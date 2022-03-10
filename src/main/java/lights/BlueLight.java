package lights;

import com.google.common.eventbus.Subscribe;
import truck.events.BlueLightEvent;

public class BlueLight extends LEDLight {

    public BlueLight(int ledCount) {
        super(ledCount, LightColor.BLUE);
    }

    @Subscribe
    public void receive(BlueLightEvent event) {
        setOn(event.getState());
    }

}

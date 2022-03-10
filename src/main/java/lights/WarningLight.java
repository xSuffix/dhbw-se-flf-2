package lights;

import com.google.common.eventbus.Subscribe;
import truck.events.BlueLightEvent;
import truck.events.WarningLightEvent;

public class WarningLight extends LEDLight {

    public WarningLight(int ledCount) {
        super(ledCount, LightColor.ORANGE);
    }

    @Subscribe
    public void receive(WarningLightEvent event){
        setOn(event.getState());
    }
}

package lights;

import com.google.common.eventbus.Subscribe;
import truck.events.RoofLightEvent;

public class RoofLight extends HeadLight {
    @Subscribe
    public void receive(RoofLightEvent event){
        setOn(event.getState());
    }
}

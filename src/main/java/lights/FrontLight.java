package lights;

import com.google.common.eventbus.Subscribe;
import truck.events.FrontLightEvent;

public class FrontLight extends HeadLight {
    @Subscribe
    public void receive(FrontLightEvent event) {
        System.out.println("Received frontlight event : " + event.getState());
        setOn(event.getState());
    }
}

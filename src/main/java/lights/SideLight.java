package lights;

import com.google.common.eventbus.Subscribe;
import truck.events.SideLightEvent;

public class SideLight extends HeadLight {
    @Subscribe
    public void receive(SideLightEvent event){
        setOn(event.getState());
    }
}

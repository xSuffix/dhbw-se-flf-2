package lights;

import truck.events.Subscriber;

public abstract class Light extends Subscriber {

    protected LightColor color;
    private boolean on;

    public Light() {
        this.on = false;
    }

    public boolean isOn() {
        return this.on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public void toggle() {
        this.on = !this.on;
    }

    public LightColor getColor(){
        return this.color;
    }

}

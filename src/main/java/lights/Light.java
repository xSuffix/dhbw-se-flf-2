package lights;

import enums.LightColor;

public abstract class Light {

    protected LightColor color;
    private boolean on;

    public Light() {
        this.on = false;
    }

    public boolean isOn() {
        return this.on;
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public void toggle() {
        this.on = !this.on;
    }

}

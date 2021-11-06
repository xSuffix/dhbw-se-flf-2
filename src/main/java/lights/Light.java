package lights;

import enums.LightColor;

public abstract class Light {
    private boolean on;
    protected LightColor color;

    public Light(){
        this.on = false;
    }

    public boolean isOn(){
        return this.on;
    }

    public void turnOn(){
        this.on = true;
    }

    public void turnOff(){
        this.on = false;
    }
}

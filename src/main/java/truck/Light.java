package truck;

public abstract class Light {
    private boolean on;

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

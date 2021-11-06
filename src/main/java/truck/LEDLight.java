package truck;

import enums.LightColor;

public abstract class LEDLight {
    private final LED[] LEDs;
    private boolean isOn;
    private LightColor color;

    public LEDLight(int ledCount,LightColor color){
        this.LEDs = new LED[ledCount];
        this.color = color;
    }

    public void turnOn(){
        for(LED led : LEDs){
            led.turnOn();
        }
    }

    public void turnOff(){
        for(LED led : LEDs){
            led.turnOff();
        }
    }
}

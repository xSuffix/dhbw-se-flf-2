package lights;

import enums.LightColor;

public abstract class LEDLight extends Light{
    private final LED[] LEDs;

    public LEDLight(int ledCount,LightColor color){
        this.LEDs = new LED[ledCount];
        for(int i = 0;i<LEDs.length;i++){
            LEDs[i] = new LED();
        }
        this.color = color;
    }
    @Override
    public void turnOn(){
        for(LED led : LEDs){
            led.turnOn();
        }
    }
    @Override
    public void turnOff(){
        for(LED led : LEDs){
            led.turnOff();
        }
    }
    @Override
    public void switchState(){
        for(LED led : LEDs){
            led.switchState();
        }
    }

}

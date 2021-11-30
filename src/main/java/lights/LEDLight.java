package lights;

import enums.LightColor;

public abstract class LEDLight extends Light {

    private final LED[] leds;

    public LEDLight(int ledCount, LightColor color) {
        this.leds = new LED[ledCount];
        for (int i = 0; i < leds.length; i++) {
            leds[i] = new LED();
        }
        this.color = color;
    }

}

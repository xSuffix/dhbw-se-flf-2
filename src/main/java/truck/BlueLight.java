package truck;

import enums.LightColor;

public class BlueLight extends LEDLight{

    public BlueLight(int ledCount) {
        super(ledCount, LightColor.BLUE);
    }
}

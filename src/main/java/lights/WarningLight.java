package lights;

import enums.LightColor;

public class WarningLight extends LEDLight{

    public WarningLight(int ledCount) {
        super(ledCount, LightColor.ORANGE);
    }
}

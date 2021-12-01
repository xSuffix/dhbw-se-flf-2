package truck;

import cabin.ButtonType;
import enums.PedalType;

public interface ICentralUnit {
    void turnSteeringWheel(int rotation);

    void buttonPress(ButtonType type);

    void pedalPress(PedalType type);

}

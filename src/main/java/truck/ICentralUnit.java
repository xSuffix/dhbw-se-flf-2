package truck;

import enums.ButtonType;
import enums.PedalType;

public interface ICentralUnit {
    void turnSteeringWheel(int rotation);
    void buttonPress(ButtonType type);
    void pedalPress(PedalType type);

}

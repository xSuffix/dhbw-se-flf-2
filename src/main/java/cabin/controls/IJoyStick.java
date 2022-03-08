package cabin.controls;

import cabin.controls.button.Button;

public interface IJoyStick {
    JoystickType getType();

    Button getFrontLeftButton();

    Button getFrontRightButton();

    Button getBackSwitch();
}

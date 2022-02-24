package cabin.controls;

public interface IJoyStick {
    JoystickType getType();

    Button getFrontLeftButton();

    Button getFrontRightButton();

    Button getBackSwitch();
}

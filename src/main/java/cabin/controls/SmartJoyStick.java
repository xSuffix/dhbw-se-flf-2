package cabin.controls;

import cabin.controls.button.Button;
import cabin.controls.button.ButtonType;
import truck.ICentralUnit;

public class SmartJoyStick implements IJoyStick {

    private final JoystickType type;
    private final Button button;
    private final Button taster;

    public SmartJoyStick(ICentralUnit centralUnit, JoystickType type) {
        this.type = type;
        if (type == JoystickType.LEFT) {
            this.taster = new Button(centralUnit, ButtonType.LEFT_JOYSTICK_BACK);
            this.button = new Button(centralUnit, ButtonType.SMART_JOYSTICK_LEFT);
        } else {
            this.taster = new Button(centralUnit, ButtonType.RIGHT_JOYSTICK_BACK);
            this.button = new Button(centralUnit, ButtonType.SMART_JOYSTICK_RIGHT);
        }
    }

    public JoystickType getType() {
        return type;
    }

    public Button getFrontLeftButton() {
        return button;
    }

    public Button getFrontRightButton() {
        return button;
    }

    public Button getBackSwitch() {
        return taster;
    }
}

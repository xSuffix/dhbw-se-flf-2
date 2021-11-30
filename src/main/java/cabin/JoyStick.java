package cabin;

import controls.Button;
import enums.ButtonType;
import enums.JoyStickType;
import truck.ICentralUnit;

public class JoyStick {

    private final ICentralUnit centralUnit;
    private final JoyStickType type;
    private final Button frontLeftButton;
    private final Button frontRightButton;
    private final Button backSwitch;

    public JoyStick(ICentralUnit centralUnit, JoyStickType type) {
        this.centralUnit = centralUnit;
        this.type = type;
        if (type == JoyStickType.LEFT) {
            this.frontLeftButton = new Button(centralUnit, ButtonType.leftJoyStickLeft);
            this.frontRightButton = new Button(centralUnit, ButtonType.leftJoyStickRight);
            this.backSwitch = new Button(centralUnit, ButtonType.leftJoyStickBack);
        } else {
            this.frontLeftButton = new Button(centralUnit, ButtonType.rightJoyStickLeft);
            this.frontRightButton = new Button(centralUnit, ButtonType.rightJoyStickRight);
            this.backSwitch = new Button(centralUnit, ButtonType.rightJoyStickBack);
        }
    }

    public JoyStickType getType() {
        return type;
    }

    public Button getFrontLeftButton() {
        return frontLeftButton;
    }

    public Button getFrontRightButton() {
        return frontRightButton;
    }

    public Button getBackSwitch() {
        return backSwitch;
    }
}


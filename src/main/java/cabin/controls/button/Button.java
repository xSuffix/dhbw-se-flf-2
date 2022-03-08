package cabin.controls.button;

import truck.ICentralUnit;

public class Button {
    private final ICentralUnit centralUnit;
    private final ButtonType type;
    private IButtonState state;

    public Button(ICentralUnit centralUnit, ButtonType type) {
        this.centralUnit = centralUnit;
        this.type = type;
        state = new ButtonStateOff();
    }

    public ICentralUnit getCentralUnit() {
        return centralUnit;
    }

    public ButtonType getType() {
        return type;
    }

    public void setState(IButtonState state) {
        this.state = state;
    }

    public void press() {
        state.press(this);
    }
}

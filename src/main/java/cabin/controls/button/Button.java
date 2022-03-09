package cabin.controls.button;

import truck.ICentralUnit;

import java.util.ArrayList;
import java.util.List;

public class Button {
    private final ICentralUnit centralUnit;
    private final ButtonType type;
    private final List<Button> synced;
    private IButtonState state;

    public Button(ICentralUnit centralUnit, ButtonType type, Button... synced) {
        this.centralUnit = centralUnit;
        this.type = type;
        this.synced = new ArrayList<>();
        for (Button button : synced) sync(button);
        sync(this);
        state = new ButtonStateOff();
    }

    public ICentralUnit getCentralUnit() {
        return centralUnit;
    }

    public ButtonType getType() {
        return type;
    }

    public List<Button> getSynced() {
        return synced;
    }

    public void sync(Button button) {
        synced.add(button);
    }

    public void setState(IButtonState state) {
        this.state = state;
    }

    public void press() {
        state.press(this);
    }
}

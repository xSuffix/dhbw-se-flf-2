package controls;

import truck.ICentralUnit;

public class Button {

    private final ICentralUnit centralUnit;
    private final ButtonType type;

    public Button(ICentralUnit centralUnit, ButtonType type) {
        this.centralUnit = centralUnit;
        this.type = type;
    }

    public void press() {
        centralUnit.buttonPress(this.type);
    }

}

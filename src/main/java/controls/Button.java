package controls;

import truck.ICentralUnit;

public record Button(ICentralUnit centralUnit, ButtonType type) {

    public void press() {
        centralUnit.buttonPress(this.type);
    }

}

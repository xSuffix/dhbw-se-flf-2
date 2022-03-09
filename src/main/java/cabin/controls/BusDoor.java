package cabin.controls;

import cabin.controls.button.Button;
import cabin.controls.button.ButtonType;
import id_card.IDCardReceiver;
import truck.ICentralUnit;

public class BusDoor {

    private final Button innerButton;
    private final Button outerButton;
    IDCardReceiver idCardReceiver;
    private boolean isOpen;
    private boolean isLocked;

    public BusDoor(ICentralUnit centralUnit, ButtonType type) {
        this.isOpen = false;
        this.isLocked = false;
        this.innerButton = new Button(centralUnit, type);
        this.outerButton = new Button(centralUnit, type);
        innerButton.sync(outerButton);
        outerButton.sync(innerButton);
        this.idCardReceiver = new IDCardReceiver(centralUnit);
    }

    public void toggleOpen() {
        setOpenIfUnlocked(!isOpen);
    }

    public void setOpenIfUnlocked(boolean open) {
        if (isOpen || !isLocked) isOpen = open;
    }

    public void toggleLock() {
        if (isLocked) {
            isLocked = false;
            setOpenIfUnlocked(true);
        } else {
            setOpenIfUnlocked(false);
            isLocked = true;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public Button getInnerButton() {
        return innerButton;
    }

    public Button getOuterButton() {
        return outerButton;
    }

    public IDCardReceiver getIdCardReceiver() {
        return idCardReceiver;
    }

}

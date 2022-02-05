package controls;

import truck.ICentralUnit;
import id_card.IDCardReceiver;

public class BusDoor {

    private final Button innerButton;
    private final Button outerButton;
    private boolean isOpen;
    private boolean isLocked;
    IDCardReceiver idCardReceiver;

    public BusDoor(ICentralUnit centralUnit, ButtonType type) {
        this.isOpen = false;
        this.innerButton = new Button(centralUnit, type);
        this.outerButton = new Button(centralUnit, type);
        this.isLocked = false;
        idCardReceiver = new IDCardReceiver(centralUnit);
    }

    public void toggleOpen() {
        if (this.isOpen) close();
        else open();
    }

    public void close() {
        this.isOpen = false;
    }

    public void open() {
        if (!this.isLocked) this.isOpen = true;
    }

    public void toggleLock() {
        if (isLocked) {
            isLocked = false;
            open();
        } else {
            close();
            isLocked = true;
        }
    }

    public boolean isOpen() {
        return isOpen;
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

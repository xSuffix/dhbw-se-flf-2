package cabin;

import controls.Button;
import enums.ButtonType;
import truck.ICentralUnit;

public class BusDoor {
    private boolean isOpen;
    private final Button innerButton;
    private final Button outerButton;

    public BusDoor(ICentralUnit centralUnit, ButtonType type){
        this.isOpen = false;
        this.innerButton = new Button(centralUnit,type);
        this.outerButton = new Button(centralUnit,type);
    }

    public void switchState(){
        this.isOpen = !this.isOpen;
    }

    public void close(){
        this.isOpen = false;
    }

    public void open(){
        this.isOpen = true;
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
}

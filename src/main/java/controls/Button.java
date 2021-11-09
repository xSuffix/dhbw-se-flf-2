package controls;

import enums.ButtonType;
import truck.CentralUnit;

public class Button {
    private CentralUnit centralUnit;
    private ButtonType type;

    public Button(CentralUnit centralUnit,ButtonType type){
        this.centralUnit = centralUnit;
        this.type = type;
    }

    public void press(){
        centralUnit.buttonPress(this.type);
    }

}

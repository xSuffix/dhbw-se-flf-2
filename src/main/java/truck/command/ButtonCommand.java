package truck.command;

import cabin.controls.button.ButtonType;
import truck.ICentralUnit;

public class ButtonCommand implements ICommand {

    private final ICentralUnit centralUnit;
    private final ButtonType type;
    private final boolean state;

    public ButtonCommand(ICentralUnit centralUnit, ButtonType type, boolean state) {
        this.centralUnit = centralUnit;
        this.type = type;
        this.state = state;
    }

    public void execute() {
        centralUnit.execute(type, state);
    }
}

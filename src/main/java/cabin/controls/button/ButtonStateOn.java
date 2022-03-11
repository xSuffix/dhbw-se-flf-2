package cabin.controls.button;

import truck.command.ButtonCommand;

public class ButtonStateOn implements IButtonState {
    @Override
    public void press(Button button) {
        //button.getCentralUnit().buttonPress(button.getType(), false);
        button.getCentralUnit().setCommand(new ButtonCommand(button.getCentralUnit(), button.getType(), false));
        button.getCentralUnit().getButtonCommand().execute();
        for (Button syncedButton : button.getSynced()) syncedButton.setState(new ButtonStateOff());
    }
}

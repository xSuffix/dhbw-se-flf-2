package cabin.controls.button;

import truck.command.ButtonCommand;

public class ButtonStateOff implements IButtonState {
    @Override
    public void press(Button button) {
        //button.getCentralUnit().buttonPress(button.getType(), true);
        button.getCentralUnit().setCommand(new ButtonCommand(button.getCentralUnit(), button.getType(), true));
        button.getCentralUnit().getCommand().execute();
        for (Button syncedButton : button.getSynced()) syncedButton.setState(new ButtonStateOn());
    }
}

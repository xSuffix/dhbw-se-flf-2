package cabin.controls.button;

public class ButtonStateOn implements IButtonState {
    @Override
    public void press(Button button) {
        button.getCentralUnit().buttonPress(button.getType(), false);
        for (Button syncedButton : button.getSynced()) syncedButton.setState(new ButtonStateOff());
    }
}

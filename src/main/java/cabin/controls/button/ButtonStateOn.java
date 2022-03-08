package cabin.controls.button;

public class ButtonStateOn implements IButtonState {
    @Override
    public void press(Button button) {
        button.getCentralUnit().buttonPress(button.getType(), false);
        button.setState(new ButtonStateOff());
    }
}

package cabin.controls.button;

public class ButtonStateOff implements IButtonState {
    @Override
    public void press(Button button) {
        button.getCentralUnit().buttonPress(button.getType(), true);
        button.setState(new ButtonStateOn());
    }
}

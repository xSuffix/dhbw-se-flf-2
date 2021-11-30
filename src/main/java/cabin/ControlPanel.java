package cabin;

import controls.Button;
import controls.FrontLauncherKnob;
import controls.RoofLauncherKnob;
import enums.ButtonType;
import truck.ICentralUnit;

public class ControlPanel {
    private final Button motorSwitch;
    private final Button warningLightSwitch;
    private final Button blueLightSwitch;
    private final Button frontHeadlightSwitch;
    private final Button roofHeadLightSwitch;
    private final FrontLauncherKnob frontLauncherKnob;
    private final RoofLauncherKnob roofLauncherKnob;

    public ControlPanel(ICentralUnit centralUnit) {
        this.motorSwitch = new Button(centralUnit, ButtonType.motorSwitch);
        this.warningLightSwitch = new Button(centralUnit, ButtonType.warningLightSwitch);
        this.blueLightSwitch = new Button(centralUnit, ButtonType.blueLightSwitch);
        this.frontHeadlightSwitch = new Button(centralUnit, ButtonType.frontHeadlightSwitch);
        this.roofHeadLightSwitch = new Button(centralUnit, ButtonType.roofHeadLightSwitch);
        this.frontLauncherKnob = new FrontLauncherKnob();
        this.roofLauncherKnob = new RoofLauncherKnob();
    }

    public Button getMotorSwitch() {
        return motorSwitch;
    }

    public Button getWarningLightSwitch() {
        return warningLightSwitch;
    }

    public Button getBlueLightSwitch() {
        return blueLightSwitch;
    }

    public Button getFrontHeadlightSwitch() {
        return frontHeadlightSwitch;
    }

    public Button getRoofHeadLightSwitch() {
        return roofHeadLightSwitch;
    }

    public FrontLauncherKnob getFrontLauncherKnob() {
        return frontLauncherKnob;
    }

    public RoofLauncherKnob getRoofLauncherKnob() {
        return roofLauncherKnob;
    }
}

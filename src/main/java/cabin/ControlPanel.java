package cabin;

import controls.*;
import truck.ICentralUnit;

public class ControlPanel {

    private final Button motorSwitch;
    private final Button warningLightSwitch;
    private final Button blueLightSwitch;
    private final Button frontHeadlightSwitch;
    private final Button roofHeadLightSwitch;
    private final Button selfProtection;
    private final TurningKnob<FrontLauncherOutput> frontLauncherKnob;
    private final TurningKnob<RoofLauncherOutput> roofLauncherKnob;

    public ControlPanel(ICentralUnit centralUnit) {
        this.motorSwitch = new Button(centralUnit, ButtonType.ELECTRIC_MOTOR);
        this.warningLightSwitch = new Button(centralUnit, ButtonType.WARNING_LIGHT);
        this.blueLightSwitch = new Button(centralUnit, ButtonType.BLUE_LIGHT);
        this.frontHeadlightSwitch = new Button(centralUnit, ButtonType.HEAD_LIGHT);
        this.roofHeadLightSwitch = new Button(centralUnit, ButtonType.ROOF_LIGHT);
        this.selfProtection = new Button(centralUnit, ButtonType.FIRE_SELF_PROTECTION);
        this.frontLauncherKnob = new TurningKnob<>(centralUnit, TurningKnobType.FRONT_LAUNCHER, FrontLauncherOutput.A);
        this.roofLauncherKnob = new TurningKnob<>(centralUnit, TurningKnobType.ROOF_LAUNCHER, RoofLauncherOutput.A);
    }

    public Button getSelfProtectionButton() {
        return selfProtection;
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

    public TurningKnob<FrontLauncherOutput> getFrontLauncherKnob() {
        return frontLauncherKnob;
    }

    public TurningKnob<RoofLauncherOutput> getRoofLauncherKnob() {
        return roofLauncherKnob;
    }

}

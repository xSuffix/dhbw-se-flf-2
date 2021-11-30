package truck;

import enums.LauncherState;

public class RoofLauncher extends WaterLauncher {

    private int firstSegmentRotation;
    private boolean secondSemgentExtended;

    public RoofLauncher(MixingUnit mixingUnit) {
        super(mixingUnit);
        firstSegmentRotation = 0;
        secondSemgentExtended = false;
    }

    public void extend() {
        if (super.getState() == LauncherState.INACTIVE) {
            this.firstSegmentRotation = 90;
            this.secondSemgentExtended = true;
            super.state = LauncherState.ACTIVE;
        } else {
            this.firstSegmentRotation = 0;
            this.secondSemgentExtended = false;
            super.state = LauncherState.INACTIVE;
        }
    }

    public int getFirstSegmentRotation() {
        return firstSegmentRotation;
    }

    public boolean isSecondSegmentExtended() {
        return secondSemgentExtended;
    }
}

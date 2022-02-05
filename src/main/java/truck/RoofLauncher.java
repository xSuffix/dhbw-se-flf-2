package truck;

import enums.LauncherState;

public class RoofLauncher extends WaterLauncher {

    private int firstSegmentRotation;
    private boolean secondSegmentExtended;

    public RoofLauncher(IMixingUnit mixingUnit) {
        super(mixingUnit);
        firstSegmentRotation = 0;
        secondSegmentExtended = false;
    }

    public void extend() {
        if (super.getState() == LauncherState.INACTIVE) {
            this.firstSegmentRotation = 90;
            this.secondSegmentExtended = true;
            super.state = LauncherState.ACTIVE;
        } else {
            this.firstSegmentRotation = 0;
            this.secondSegmentExtended = false;
            super.state = LauncherState.INACTIVE;
        }
    }

    public int getFirstSegmentRotation() {
        return firstSegmentRotation;
    }

    public boolean isSecondSegmentExtended() {
        return secondSegmentExtended;
    }

}

package truck;

import enums.LauncherState;

public class FrontLauncher extends WaterLauncher {

    private int rotation;

    public FrontLauncher(MixingUnit mixingUnit) {
        super(mixingUnit);
        this.rotation = 0;
    }

    public void pan() {
        if (super.state == LauncherState.INACTIVE) {
            super.state = LauncherState.ACTIVE;
            this.rotation = 90;
        } else {
            super.state = LauncherState.INACTIVE;
            this.rotation = 0;
        }
    }

    public int getRotation() {
        return this.rotation;
    }

}

package truck;

import enums.LauncherState;
import enums.MixingRatio;

public abstract class WaterLauncher {

    private final IMixingUnit mixingUnit;
    protected LauncherState state;
    private MixingRatio ratio;

    public WaterLauncher(IMixingUnit mixingUnit) {
        this.mixingUnit = mixingUnit;
        this.state = LauncherState.INACTIVE;
        this.ratio = MixingRatio.A;
    }

    public void sprayWater(int amount) {
        System.out.println("Sprühe " + this.mixingUnit.getMixedAgent(amount, this.ratio).size() + " Einheiten Löschmittel!");
    }

    public LauncherState getState() {
        return this.state;
    }

    public MixingRatio getRatio() {
        return ratio;
    }

    public void switchRatio() {
        if (state == LauncherState.ACTIVE) {
            this.ratio = this.ratio.getNext();
        }
    }

}

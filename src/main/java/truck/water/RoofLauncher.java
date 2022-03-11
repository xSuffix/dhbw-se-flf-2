package truck.water;

import truck.visitor.ISelfCheckVisitor;

public class RoofLauncher extends Launcher {

    private int firstSegmentRotation;
    private boolean secondSegmentExtended;

    public RoofLauncher(Object mixingUnit, ITank waterTank, ITank foamTank) {
        super(mixingUnit, waterTank, foamTank);
        firstSegmentRotation = 0;
        secondSegmentExtended = false;
    }

    @Override
    public boolean accept(ISelfCheckVisitor visitor) {
        return visitor.visit(this);
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

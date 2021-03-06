package truck.water;

import truck.visitor.ISelfCheckVisitor;

public class FrontLauncher extends Launcher {

    private int rotation;

    public FrontLauncher(Object mixingUnit, ITank waterTank, ITank foamTank) {
        super(mixingUnit, waterTank, foamTank);
        this.rotation = 0;
    }

    @Override
    public boolean accept(ISelfCheckVisitor visitor) {
        return visitor.visit(this);
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

package truck.visitor;

import truck.water.FloorSprayingNozzle;
import truck.water.FrontLauncher;
import truck.water.RoofLauncher;

public class SelfCheckVisitor implements ISelfCheckVisitor {

    @Override
    public boolean visit(FloorSprayingNozzle floorSprayingNozzle) {
        System.out.println("Testing floor spraying nozzle...");
        return floorSprayingNozzle.sprayWater(10) == 10;
    }

    @Override
    public boolean visit(FrontLauncher frontLauncher) {
        System.out.println("Testing front launcher...");
        frontLauncher.pan();
        if (frontLauncher.getRotation() != 90)
            return false;

        frontLauncher.pan();
        if (frontLauncher.getRotation() != 0)
            return false;

        return frontLauncher.sprayExtinguisher(10) == 10;
    }

    @Override
    public boolean visit(RoofLauncher roofLauncher) {
        System.out.println("Testing roof launcher...");
        roofLauncher.extend();
        if (!roofLauncher.isSecondSegmentExtended() || roofLauncher.getFirstSegmentRotation() != 90)
            return false;

        roofLauncher.extend();
        if (roofLauncher.isSecondSegmentExtended() || roofLauncher.getFirstSegmentRotation() != 0)
            return false;


        return roofLauncher.sprayExtinguisher(10) == 10;
    }
}

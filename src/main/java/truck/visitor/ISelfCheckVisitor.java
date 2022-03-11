package truck.visitor;

import truck.water.FloorSprayingNozzle;
import truck.water.FrontLauncher;
import truck.water.RoofLauncher;

public interface ISelfCheckVisitor {

    boolean visit(FloorSprayingNozzle floorSprayingNozzle);

    boolean visit(FrontLauncher frontLauncher);

    boolean visit(RoofLauncher roofLauncher);

}

package truck.water;

import truck.visitor.ISelfCheckVisitor;
import truck.visitor.ISelfTestElement;

public class FloorSprayingNozzle implements ISelfTestElement {
    private final ITank waterTank;

    public FloorSprayingNozzle(ITank water) {
        this.waterTank = water;
    }

    public int sprayWater(int amount) {
        if (amount > 100) amount = 100;
        int output = waterTank.getAgent(amount).length;
        System.out.println("[FloorSprayingNozzle] Spraying " + output + " units of water");
        return output;
    }

    @Override
    public boolean accept(ISelfCheckVisitor visitor) {
        return visitor.visit(this);
    }
}

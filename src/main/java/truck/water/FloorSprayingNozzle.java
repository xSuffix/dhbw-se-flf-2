package truck.water;

public class FloorSprayingNozzle {
    private final ITank waterTank;

    public FloorSprayingNozzle(ITank water) {
        this.waterTank = water;
    }

    public void sprayWater(int amount) {
        if (amount > 100) amount = 100;
        System.out.println("Sprühe " + waterTank.getAgent(amount).length + " Einheiten Wasser!");
    }

}

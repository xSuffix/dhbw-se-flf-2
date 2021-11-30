package truck;

public class FloorSprayingNozzle {
    private final Tank waterTank;

    public FloorSprayingNozzle(Tank water) {
        this.waterTank = water;
    }

    public void sprayWater(int amount) {
        if (amount > 100) amount = 100;
        System.out.println("Sprühe " + waterTank.getAgent(amount).length + " Einheiten Wasser!");
    }
}

package drive.battery;

import drive.battery.cell.BatteryCell;
import drive.battery.cell.MainCell;

import java.util.ArrayList;
import java.util.List;

public class Battery {

    private final List<MainCell> mainCells;

    public Battery() {
        mainCells = new ArrayList<MainCell>();
        for (int i = 0; i < 100; i++)
            mainCells.add(new MainCell());
    }

    public int getCurrentCapacity() {
        int capacity = 0;
        for (BatteryCell mainCell : mainCells)
            capacity += mainCell.getCurrentCharge();
        return capacity;
    }

    public int getTotalCapacity() {
        int total = 0;
        for (BatteryCell mainCell : mainCells) {
            total += mainCell.getTotalCapacity();
        }
        return total;
    }

    public void charge(int amount) {

        for (int i = 0; i < amount; i++) {
            for (BatteryCell mainCell : mainCells) {
                if (mainCell.addCharge()) {
                    break;
                }
            }
        }

    }

    public int takeEnergy(int amount) {
        int energy = 0;

        if (amount < 0)
            return 0;

        if (amount > getCurrentCapacity())
            amount = getCurrentCapacity();

        for (int i = 0; i < amount; i++) {
            for (BatteryCell mainCell : mainCells) {
                if (mainCell.takeEnergy()){
                    energy++;
                    break;
                }
            }
        }

        return energy;
    }

}

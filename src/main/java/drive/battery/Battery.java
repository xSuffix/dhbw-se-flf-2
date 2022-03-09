package drive.battery;

import java.util.ArrayList;
import java.util.List;

import drive.battery.cell.BatteryCell;
import drive.battery.cell.MainCell;

public class Battery {

    private final List<MainCell> mainCells;

    public Battery(){
        mainCells = new ArrayList<MainCell>();
        for (int i = 0; i<100;i++)
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
        for (BatteryCell mainCell : mainCells){
            total += mainCell.getTotalCapacity();
        }
        return total;
    }

    public void charge(int amount){

        for (int i = 0; i < amount; i++){ 
            for (BatteryCell mainCell : mainCells){
                if (mainCell.addCharge()){
                    break;
                }
            }
        }
        
    }

    public int takeEnergy(int amount){
        int energy = 0;

        if (amount < 0)
            return 0;

        if (amount > getCurrentCapacity())
            amount = getCurrentCapacity();
        
            for (int i = 0; i < amount; i++){ 
                for (BatteryCell mainCell : mainCells){
                    if (mainCell.takeEnergy())
                        energy++;
                        break;
                }
            }

        return energy;
    }

    // private final int[][][] capacity;

    // public Battery(int l, int h, int b) {
    //     this.capacity = new int[l][h][b];
    //     for (int[][] le : capacity) {
    //         for (int[] ho : le) {
    //             Arrays.fill(ho, 1);
    //         }
    //     }
    // }

    // public int getCurrentCapacity() {
    //     int cap = 0;
    //     for (int[][] l : capacity) {
    //         for (int[] h : l) {
    //             for (int b : h) {
    //                 cap += b;
    //             }
    //         }
    //     }
    //     return cap;
    // }

    // public int getTotalCapacity() {
    //     return capacity.length * capacity[0].length * capacity[0][0].length;
    // }

    // public void charge(int amount) {
    //     for (int i = 0; i < capacity.length; i++) {
    //         for (int j = 0; j < capacity[i].length; j++) {
    //             for (int k = 0; k < capacity[i][j].length; k++) {
    //                 if (capacity[i][j][k] == 0 && amount > 0) {
    //                     capacity[i][j][k] = 1;
    //                     amount--;
    //                 }
    //             }
    //         }
    //     }
    // }

    // public int takeEnergy(int amount) {
    //     int energy = 0;
    //     for (int b = capacity.length - 1; b >= 0; b--) {
    //         for (int h = capacity[b].length - 1; h >= 0; h--) {
    //             for (int l = capacity[b][h].length - 1; l >= 0; l--) {
    //                 if (capacity[b][h][l] == 1 && amount > 0) {
    //                     capacity[b][h][l] = 0;
    //                     energy++;
    //                     amount--;
    //                 }
    //             }
    //         }
    //     }
    //     return energy;
    // }

}

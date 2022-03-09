package drive.battery;

import java.util.ArrayList;
import java.util.List;

public abstract class BatteryCell {
    
    protected BatteryCell parentUnit;
    protected List<BatteryCell> cellList;

    public BatteryCell(){
        this.cellList = new ArrayList<BatteryCell>();
    }

    public BatteryCell(BatteryCell parent){
        this.parentUnit = parent;
        this.cellList = new ArrayList<BatteryCell>();
    }
    
    public void addUnit(BatteryCell unit){
        cellList.add(unit);
    }

    public final boolean isComposite() {
        return !cellList.isEmpty();
    }

    public BatteryCell getParentUnit() {
        return this.parentUnit;
    }

    public List<BatteryCell> getUnitList(){
        return this.cellList;
    }

    public int getCurrentCharge(){
        int charge = 0;
        for (BatteryCell cell : cellList)
            charge += cell.getCurrentCharge();
        
        return charge;
    }

    public boolean addCharge() {
        for (BatteryCell cell : cellList){
            if (cell.addCharge())
                return true;
        }
        return false;
    }

    public boolean takeEnergy() {
        for (BatteryCell cell : cellList){
            if (cell.takeEnergy())
                return true;
        }
        return false;
    }

    public int getTotalCapacity(){
        int total = 0;
        for (BatteryCell cells : cellList)
            total += cells.getTotalCapacity();
        return total;
    }
}

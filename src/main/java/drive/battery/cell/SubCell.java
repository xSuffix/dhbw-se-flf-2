package drive.battery.cell;

public class SubCell extends BatteryCell{
    
    public SubCell(BatteryCell parent) {
        super(parent);
        for (int i = 0; i<10;i++)
            addUnit(new Cell(this));
    }

}

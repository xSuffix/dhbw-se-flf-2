package drive.battery.cell;

public class MainCell extends BatteryCell {

    public MainCell() {
        for (int i = 0; i < 100; i++)
            addUnit(new SubCell(this));
    }

}

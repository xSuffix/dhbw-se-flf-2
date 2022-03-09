package drive.battery;

public class Cell extends BatteryCell{
    private boolean isCharged;

    public Cell(BatteryCell parent){
        super(parent);
        isCharged = false;
    }

    @Override
    public int getCurrentCharge(){
        if (isCharged)
            return 1;
        else
            return 0;
    }

    @Override
    public boolean addCharge(){
        if (isCharged)
            return false;
        
        isCharged = true;
        return true;
    }

    @Override
    public boolean takeEnergy(){
        if (!isCharged)
            return false;
        
        isCharged = false;
        return true;
    }

    @Override
    public int getTotalCapacity(){
        return 1;
    }
}

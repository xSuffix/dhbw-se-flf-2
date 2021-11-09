package drive;

public class BatteryBox {

    private final Battery[][] batteries;

    public BatteryBox(){
        batteries = new Battery[2][2];
        for(Battery[] row : batteries){
            for(Battery b : row){
                b = new Battery(100,10,100);
            }
        }
    }

    public int getCurrentCharge(){
        int charge = 0;
        for(Battery[] row : batteries){
            for(Battery b : row){
                charge += b.getCurrentCapacity();
            }
        }
        return charge;
    }

    public int getMaxCharge(){
        int charge = 0;
        for(Battery[] row : batteries){
            for(Battery b : row){
                charge += b.getTotalCapacity();
            }
        }
        return charge;
    }

    public float getCurrentChargePercentage(){
        return (float)getCurrentCharge()/getMaxCharge();
    }

    public void charge(int amount){
        for(Battery[] row : batteries){
            for(Battery b : row){
                int chargeDelta = b.getTotalCapacity() - b.getCurrentCapacity();
                if (amount > 0){
                    if (amount > chargeDelta){
                        b.charge(chargeDelta);
                        amount =- chargeDelta;
                    } else {
                        b.charge(amount);
                        amount = 0;
                    }
                }
            }
        }
    }

    public int takeEnergy(int amount){
        for(Battery[] row : batteries){
            for(Battery b : row) {
                if(b.getCurrentCapacity() >= amount && amount > 0){
                    b.takeEnergy(amount);
                    amount = 0;
                } else if (amount > 0){
                    amount =- b.getCurrentCapacity();
                    b.takeEnergy(b.getCurrentCapacity());
                }
            }
        }
        return amount;
    }
}

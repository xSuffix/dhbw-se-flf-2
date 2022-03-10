package drive.battery;

public class BatteryBox implements IBatteryBox {

    private final Battery[][] batteries;

    public BatteryBox() {
        batteries = new Battery[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                batteries[i][j] = new Battery();
            }
        }
    }

    public int getCurrentCharge() {
        int charge = 0;
        for (Battery[] row : batteries) {
            for (Battery b : row) {
                charge += b.getCurrentCapacity();
            }
        }
        return charge;
    }

    public int getMaxCharge() {
        int charge = 0;
        for (Battery[] row : batteries) {
            for (Battery b : row) {
                charge += b.getTotalCapacity();
            }
        }
        return charge;
    }

    public float getCurrentChargePercentage() {
        return ((float) getCurrentCharge() / getMaxCharge()) * 100;
    }

    public void charge(int amount) {
        for (Battery[] row : batteries) {
            for (Battery b : row) {
                int chargeDelta = b.getTotalCapacity() - b.getCurrentCapacity();
                if (amount > 0) {
                    if (amount > chargeDelta) {
                        b.charge(chargeDelta);
                        amount = amount - chargeDelta;
                    } else {
                        b.charge(amount);
                        amount = 0;
                    }
                }
            }
        }
    }

    public int takeEnergy(int amount) {
        int energy = 0;
        for (Battery[] row : batteries) {
            for (Battery b : row) {
                if (b.getCurrentCapacity() >= amount && amount > 0) {
                    energy += b.takeEnergy(amount);
                    amount = 0;
                } else if (amount > 0) {
                    amount = amount - b.getCurrentCapacity();
                    energy += b.takeEnergy(b.getCurrentCapacity());
                }
            }
        }
        return energy;
    }

    public Battery[][] getBatteries() {
        return this.batteries;
    }

}

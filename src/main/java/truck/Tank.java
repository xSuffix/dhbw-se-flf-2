package truck;

import enums.ExtinguishingType;

public class Tank {

    private final ExtinguishingType type;
    private final ExtinguishingType[][][] capacity;

    public Tank(ExtinguishingType type, int l, int h, int b) {
        this.type = type;
        this.capacity = new ExtinguishingType[l][h][b];
    }

    public void fill(int amount, ExtinguishingType type) {
        if (this.type != type) throw new RuntimeException("Do not mix Extinguishing Agents in the tank!!!");
        for (int i = 0; i < capacity.length; i++) {
            for (int j = 0; j < capacity[i].length; j++) {
                for (int k = 0; k < capacity[i][j].length; k++) {
                    if (capacity[i][j][k] == null && amount > 0) {
                        capacity[i][j][k] = this.type;
                        amount--;
                    }
                }
            }
        }
    }

    public ExtinguishingType[] getAgent(int amount) {
        int amountAvail = 0;
        for (int i = 0; i < capacity.length; i++) {
            for (int j = 0; j < capacity[i].length; j++) {
                for (int k = 0; k < capacity[i][j].length; k++) {
                    if (capacity[i][j][k] == this.type && amount > 0) {
                        capacity[i][j][k] = null;
                        amount--;
                        amountAvail++;
                    }
                }
            }
        }
        ExtinguishingType[] amountReturned = new ExtinguishingType[amountAvail];
        for (ExtinguishingType unit : amountReturned) {
            unit = this.type;
        }
        return amountReturned;
    }

    public int getTotalCapacity() {
        return capacity.length * capacity[0].length * capacity[0][0].length;
    }

    public int getCurrentCapacity() {
        int cap = 0;
        for (ExtinguishingType[][] l : capacity) {
            for (ExtinguishingType[] h : l) {
                for (ExtinguishingType b : h) {
                    if (b != null) {
                        cap += 1;
                    }
                }
            }
        }
        return cap;
    }

    public float getCurrentFillPercentage() {
        return ((float) getCurrentCapacity() / getTotalCapacity()) * 100;
    }

    public ExtinguishingType getType() {
        return this.type;
    }

}

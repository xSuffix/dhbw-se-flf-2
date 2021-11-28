package truck;

import enums.ExtinguishingType;

public class Tank {

    private ExtinguishingType type;
    private ExtinguishingType[][][] capacity;

    public Tank(ExtinguishingType type, int l, int h, int b){
        this.type = type;
        this.capacity = new ExtinguishingType[l][h][b];
    }

    public void fill(int amount, ExtinguishingType type){
        if (this.type != type) throw new RuntimeException("Do not mix Extinguishing Agents in the tank!!!");
        for (ExtinguishingType[][] b : capacity){
            for (ExtinguishingType[] h : b){
                for (ExtinguishingType l : h){
                    if (l == null && amount > 0){
                        l = this.type;
                        amount--;
                    }
                }
            }
        }
    }

    public ExtinguishingType[] getAgent(int amount){
        int amountAvail = 0;
        for (ExtinguishingType[][] b : capacity){
            for (ExtinguishingType[] h : b){
                for (ExtinguishingType l : h){
                    if (l == this.type && amount > 0){
                        l = null;
                        amount--;
                        amountAvail ++;
                    }
                }
            }
        }
        ExtinguishingType[] amountReturned = new ExtinguishingType[amountAvail];
        for (ExtinguishingType unit : amountReturned){
            unit = this.type;
        }
        return amountReturned;
    }

    public int getTotalCapacity(){
        return capacity.length * capacity[0].length * capacity[0][0].length;
    }

    public ExtinguishingType getType(){
        return this.type;
    }
}

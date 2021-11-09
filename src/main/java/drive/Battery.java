package drive;

public class Battery {

    private final int[][][] capacity;

    public Battery(int l, int h,int b){
        this.capacity = new int[l][h][b];
        for (int[][] x : capacity){
            for (int [] y : x){
                for (int z : y){
                    z = 0;
                }
            }
        }
    }


    public int getCurrentCapacity() {
        int cap = 0;
        for (int[][] l : capacity){
            for (int [] h : l){
                for (int b : h){
                    cap += b;
                }
            }
        }
        return cap;
    }

    public int getTotalCapacity(){
        return capacity.length * capacity[0].length * capacity[0][0].length;
    }

    public void charge(int amount){
        for (int[][] l : capacity) {
            for (int[] h : l) {
                for (int b : h) {
                    if (b == 0 && amount > 0){
                        b = 1;
                        amount--;
                    }
                }
            }
        }
    }

    public int takeEnergy(int amount){
        int energy = 0;
        for(int b = capacity.length-1;b >= 0;b--){
            for(int h = capacity[b].length-1;h >= 0;h--){
                for(int l = capacity[b][h].length-1;l >= 0;l--){
                    if(l == 1 && amount != 0){
                        l = 0;
                        energy++;
                        amount--;
                    }
                }
            }
        }
        return energy;
    }

}

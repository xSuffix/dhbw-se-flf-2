package enums;

public enum MixingRatio {
    A(0),
    B(3),
    C(5),
    D(10);

    private final int value;

    MixingRatio(int i) {
        this.value = i;
    }

    public int getValue(){
        return this.value;
    }

    public MixingRatio getPrevious(){
        switch (this.getValue()){
            case 0 -> {
                return MixingRatio.D;
            }
            case 3 -> {
                return MixingRatio.A;
            }
            case 5 -> {
                return MixingRatio.B;
            }
            case 10 -> {
                return MixingRatio.C;
            }

        }
        return null;
    }

    public MixingRatio getNext(){
        switch (this.getValue()){
            case 0 -> {
                return MixingRatio.B;
            }
            case 3 -> {
                return MixingRatio.C;
            }
            case 5 -> {
                return MixingRatio.D;
            }
            case 10 -> {
                return MixingRatio.A;
            }

        }
        return null;
    }
}

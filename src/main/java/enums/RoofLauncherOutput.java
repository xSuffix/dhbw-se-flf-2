package enums;

public enum RoofLauncherOutput {
    A(500),
    B(1000),
    C(2500);

    private final int value;

    RoofLauncherOutput(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public RoofLauncherOutput getPrevious() {
        switch (this.getValue()) {
            case 500 -> {
                return RoofLauncherOutput.C;
            }
            case 1000 -> {
                return RoofLauncherOutput.A;
            }
            case 2500 -> {
                return RoofLauncherOutput.B;
            }

        }
        return null;
    }

    public RoofLauncherOutput getNext() {
        switch (this.getValue()) {
            case 500 -> {
                return RoofLauncherOutput.B;
            }
            case 1000 -> {
                return RoofLauncherOutput.C;
            }
            case 2500 -> {
                return RoofLauncherOutput.A;
            }

        }
        return null;
    }
}

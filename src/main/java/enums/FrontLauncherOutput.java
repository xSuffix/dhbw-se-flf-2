package enums;

public enum FrontLauncherOutput {
    A(500),
    B(1000),
    C(1500),
    D(2000),
    E(2500),
    F(3000),
    G(3500);

    private final int value;

    FrontLauncherOutput(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public FrontLauncherOutput getPrevious() {
        switch (this.getValue()) {
            case 500 -> {
                return FrontLauncherOutput.G;
            }
            case 1000 -> {
                return FrontLauncherOutput.A;
            }
            case 1500 -> {
                return FrontLauncherOutput.B;
            }
            case 2000 -> {
                return FrontLauncherOutput.C;
            }
            case 2500 -> {
                return FrontLauncherOutput.D;
            }
            case 3000 -> {
                return FrontLauncherOutput.E;
            }
            case 3500 -> {
                return FrontLauncherOutput.F;
            }

        }
        return null;
    }

    public FrontLauncherOutput getNext() {
        switch (this.getValue()) {
            case 500 -> {
                return FrontLauncherOutput.B;
            }
            case 1000 -> {
                return FrontLauncherOutput.C;
            }
            case 1500 -> {
                return FrontLauncherOutput.D;
            }
            case 2000 -> {
                return FrontLauncherOutput.E;
            }
            case 2500 -> {
                return FrontLauncherOutput.F;
            }
            case 3000 -> {
                return FrontLauncherOutput.G;
            }
            case 3500 -> {
                return FrontLauncherOutput.A;
            }

        }
        return null;
    }

}

package cabin.controls;

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

}
